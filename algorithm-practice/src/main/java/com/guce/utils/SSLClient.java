package com.guce.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Administrator on 2016/11/24.
 */
public class SSLClient {

    private static final long WARN_TIME = 800L;
    private static final String _127_0_0_1 = "127.0.0.1";
    private static final Logger logger = LoggerFactory.getLogger(SSLClient.class);
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_TIMEOUT = 4;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 4000;
    public static final ThreadLocal<String> ip_session = new ThreadLocal();
    private static PoolingClientConnectionManager cm = null;

    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager truseAllManager = new X509TrustManager(){


        public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    };

    /**
     * 访问https的网站
     * @param httpclient
     */
    private static void enableSSL(DefaultHttpClient httpclient){
//调用ssl
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { truseAllManager }, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", 443, sf);
            httpclient.getConnectionManager().getSchemeRegistry().register(https);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SSLClient() {
    }

    public static HttpClient getHttpClient(int timeOut) {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000);
        HttpConnectionParams.setSoTimeout(httpParams, timeOut > 0?timeOut * 1000:4000);
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        HttpClientParams.setRedirecting(httpParams, true);
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        DefaultHttpClient httpClient = new DefaultHttpClient(cm, httpParams);
        enableSSL(httpClient);
        return httpClient;
    }

    private static void logIfSlowURLWithConnectionStats(String uri, long start) {
        long offset = System.currentTimeMillis() - start;
        if(offset > 800L) {
            logSlowURLWithConnectionStats(offset, uri);
        }

    }

    protected static void logSlowURLWithConnectionStats(long offset, String url) {
        PoolStats totalStats = cm.getTotalStats();

        try {
            URL e = new URL(url);
            logger.warn("HTTP_take much time-{}| {} cost {} millis. ### http connection pool, max: {}, acitve: {}, leased: {}, pending: {}", new Object[]{e.getHost(), url, Long.valueOf(offset), Integer.valueOf(totalStats.getMax()), Integer.valueOf(totalStats.getAvailable()), Integer.valueOf(totalStats.getLeased()), Integer.valueOf(totalStats.getPending())});
        } catch (MalformedURLException var5) {
            ;
        }

    }

    protected static void logConnectionStats(HttpRoute route, String url) {
        if(logger.isDebugEnabled()) {
            PoolStats stats = cm.getStats(route);
            logger.debug("HTTP_connection route pool for-{}| max: {}, acitve: {}, leased: {}, pending: {}", new Object[]{route.getProxyHost(), Integer.valueOf(stats.getMax()), Integer.valueOf(stats.getAvailable()), Integer.valueOf(stats.getLeased()), Integer.valueOf(stats.getPending())});
        }

    }

    public static String doGet(String url) throws Exception {
        return doGet(url, "UTF-8");
    }

    public static String doGet(String url, String charset) throws Exception {
        return doGet(url, charset, 4);
    }

    public static String doGet(String url, int timeout) throws Exception {
        return doGet(url, "UTF-8", timeout);
    }

    public static String doGet(String url, String charset, int timeout) throws Exception {
        return doGet(url, charset, timeout, true);
    }

    public static String doGet(String url, String charset, int timeout, boolean isLog) throws Exception {
        String responseString = "";
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        HttpGet httpRequest = null;

        try {
            long e = System.currentTimeMillis();
            httpClient = getHttpClient(timeout);
            httpRequest = new HttpGet(url);

            httpRequest.setHeader("Cookie","AlteonP=0a02eb040a02ebc552d28d692378; JSESSIONID=00009Sa9D1iTdlOCQG3Fs-VxDqw:196iqvou7; BIGipServernginxformobile=82510346.50215.0000");
            updateCommonHeader(httpRequest);
            httpResponse = httpClient.execute(httpRequest);
            if(!isSuccess(httpResponse.getStatusLine().getStatusCode())) {

                if(isLog) {
                    logger.warn("HTTP_get error status code-{}| the url is: {}, response: {}", new Object[]{Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), url, new String(getResponseEntityAsByteArray(httpResponse)), charset});
                }
                System.out.println(String.format("HTTP_get error status code- %s|\n the url is: %s, \nresponse: %s", new Object[]{Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), url, new String(getResponseEntityAsByteArray(httpResponse)), charset}));
                httpRequest.abort();
                return null;
            } else {
                if(httpResponse.getEntity() != null) {
                    byte[] bytes = getResponseEntityAsByteArray(httpResponse);
                    if(isLog && (bytes.length < 15 || bytes.length > 1024) && logger.isInfoEnabled()) {
                        String responseMinStr = getMinHttpResponeStringForLog(bytes, charset);
                        logger.info("HTTP_get| url: {},length is {}, response: {}", new Object[]{url, Integer.valueOf(bytes != null?bytes.length:0), responseMinStr});
                    }

                    responseString = new String(bytes, charset);
                    responseString = filterHtml(responseString);
                    if(isLog && logger.isDebugEnabled()) {
                        logger.debug("HTTP_get| {}, response: {}", url, responseString);
                    }
                }

                logIfSlowURLWithConnectionStats(url, e);
                return responseString;
            }
        } catch (Exception var12) {
            if(isLog) {
                logger.error("HTTP_get-{}| {}", new Object[]{var12.getMessage(), url, !(var12 instanceof IOException) && !(var12 instanceof ClientProtocolException)?var12:null});
            }

            if(httpRequest != null) {
                httpRequest.abort();
            }

            throw var12;
        }
    }

    private static boolean isSuccess(int respStatusCode) {
        return respStatusCode == 200 || respStatusCode == 201;
    }

    private static void updateCommonHeader(HttpRequestBase httpRequest) {
        httpRequest.addHeader("Referer", "http://mapi.vip.com/");
        httpRequest.addHeader("Accept-Encoding", "gzip, deflate");
        populateRequestIp(httpRequest);
    }

    private static void populateRequestIp(HttpRequestBase httpRequest) {
        String client_id = (String)ip_session.get();
        if(StringUtils.isEmpty(client_id)) {
            client_id = "127.0.0.1";
        }

        httpRequest.setHeader("X-Forwarded-For", client_id);
        if(logger.isDebugEnabled()) {
            logger.debug("HTTP_request ip-{}|", client_id);
        }

    }

    public InputStream doGet4stream(String url) throws ClientProtocolException, IOException {
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse httpResponse = getHttpClient(60).execute(httpRequest);
        return !isSuccess(httpResponse.getStatusLine().getStatusCode())?httpResponse.getEntity().getContent():null;
    }

    private static String filterHtml(String result) {
        if(null != result) {
            result = result.replace("&#039;", "\'");
            result = result.replace("&amp;", "&");
            result = result.replace("&nbsp;", " ");
        }

        return result;
    }

    public static String doPost(String url) {
        return doPost(url, (String)"UTF-8", (Map)null);
    }

    public static String doPost(String url, String content) {
        return doPost(url, content, (String)"UTF-8", 4);
    }

    public static String doPost(String url, String content, String charset, int timeout) {
        return doPost(url, content, (String)charset, timeout, (Map)null);
    }

    public static String doPost(String url, Map<String, Object> parameters) {
        return doPost(url, "UTF-8", parameters);
    }

    public static String doPost(String url, String charset, Map<String, Object> parameters) {
        return doPost(url, charset, (Map)parameters, 4);
    }

    public static String doPost(String url, String charset, Map<String, Object> parameters, int timeout) {
        return doPost(url, charset, (Map)parameters, timeout, (Map)null);
    }

    public static String doPost(String url, String content, String charset, int timeout, Map<String, String> headerMap) {
        if(logger.isInfoEnabled()) {
            logger.info("HTTP_post-{}| content: {}, header: {}", new Object[]{url, content, JSONObject.toJSONString(headerMap)});
        }

        HttpClient httpClient = getHttpClient(timeout);
        HttpPost httpRequest = new HttpPost(url);

        try {
            StringEntity e = new StringEntity(content, charset);
            httpRequest.setEntity(e);
            addHeaderForHttpRequestBase(httpRequest, headerMap);
            String var8 = doPost((HttpClient)httpClient, (HttpEntityEnclosingRequestBase)httpRequest);
            return var8;
        } catch (Exception var12) {
            logger.error("HTTP_post-{} - {}| content: {}, header: {}", new Object[]{url, var12.getMessage(), content, JSONObject.toJSONString(headerMap), var12 instanceof UnsupportedEncodingException?null:var12});
            return null;
        } finally {
            ;
        }
    }

    public static String doPost(String url, Map<String, Object> parameters, Map<String, String> headerMap) {
        return doPost(url, "UTF-8", (Map)parameters, 4, headerMap);
    }

    public static String doPost(String url, String charset, Map<String, Object> parameters, int timeout, Map<String, String> headerMap) {
        if(logger.isInfoEnabled()) {
            logger.info("HTTP_post-{}| param: {}, header: {}", new Object[]{url, JSONObject.toJSONString(parameters), JSONObject.toJSONString(headerMap)});
        }

        HttpClient httpClient = getHttpClient(timeout);
        HttpPost httpRequest = new HttpPost(url);
        if(parameters != null && !parameters.isEmpty()) {
            ArrayList e = new ArrayList(parameters.size());
            Iterator e1 = parameters.keySet().iterator();

            while(e1.hasNext()) {
                String key = (String)e1.next();
                e.add(new BasicNameValuePair(key, String.valueOf(parameters.get(key))));
            }

            try {
                httpRequest.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));
            } catch (UnsupportedEncodingException var10) {
                logger.warn("HTTP_post-{} encode parameter error| param: {}, header: {}", new Object[]{url, JSONObject.toJSONString(parameters), JSONObject.toJSONString(headerMap)});
            }
        }

        addHeaderForHttpRequestBase(httpRequest, headerMap);

        try {
            String e2 = doPost((HttpClient)httpClient, (HttpEntityEnclosingRequestBase)httpRequest);
            if(logger.isDebugEnabled()) {
                logger.debug("HTTP_post-{}| param: {}, header: {}, response: {}", new Object[]{url, JSONObject.toJSONString(parameters), JSONObject.toJSONString(parameters), e2});
            }

            return e2;
        } catch (Exception var11) {
            logger.error("HTTP_post-{} - {}| param: {}, header: {}", new Object[]{url, var11.getMessage(), JSONObject.toJSONString(parameters), JSONObject.toJSONString(headerMap), !(var11 instanceof UnsupportedEncodingException) && !(var11 instanceof ClientProtocolException)?var11:null});
            return null;
        }
    }

    public static String doPost(HttpClient httpClient, HttpEntityEnclosingRequestBase httpRequest) throws Exception {
        HttpEntity rspEntity = null;

        try {
            long e = System.currentTimeMillis();
            updateCommonHeader(httpRequest);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            if(!isSuccess(httpResponse.getStatusLine().getStatusCode())) {
                logger.warn("HTTP_post error status code-{}| the url is: {}, response: {}", new Object[]{Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), httpRequest.getURI(), new String(getResponseEntityAsByteArray(httpResponse)), "UTF-8"});
                httpRequest.abort();
                Object bytes1 = null;
                return (String)bytes1;
            }

            rspEntity = httpResponse.getEntity();
            if(rspEntity != null) {
                byte[] bytes = getResponseEntityAsByteArray(httpResponse);
                String e1;
                if(logger.isInfoEnabled() && (bytes.length < 15 || bytes.length > 1024)) {
                    e1 = getMinHttpResponeStringForLog(bytes, "UTF-8");
                    logger.info("HTTP_post| url: {},length is {}, response: {}", new Object[]{httpRequest.getURI(), Integer.valueOf(bytes != null?bytes.length:0), e1});
                }

                e1 = new String(bytes, "UTF-8");
                return e1;
            }

            logIfSlowURLWithConnectionStats(httpRequest.getURI().toString(), e);
        } catch (Exception var18) {
            httpRequest.abort();
            throw var18;
        } finally {
            try {
                if(rspEntity != null) {
                    EntityUtils.consume(rspEntity);
                }
            } catch (IOException var17) {
                logger.error("HTTP_post fail consume the response with Exception {}|", var17.getMessage());
            }

        }

        return null;
    }

    private static byte[] getResponseEntityAsByteArray(HttpResponse httpResponse) throws IOException {
        Header contentEncodingHeader = httpResponse.getFirstHeader("Content-Encoding");
        Object bytes = null;
        byte[] bytes1;
        if(contentEncodingHeader != null && contentEncodingHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
            bytes1 = EntityUtils.toByteArray(new GzipDecompressingEntity(httpResponse.getEntity()));
        } else {
            bytes1 = EntityUtils.toByteArray(httpResponse.getEntity());
        }

        return bytes1;
    }

    public static void setClienIp(String ip) {
        ip_session.set(ip);
    }

    public static String getClientIp() {
        return (String)ip_session.get();
    }

    public static String doDelete(String url) {
        return doDelete(url, "UTF-8", (Map)null);
    }

    public static String doDelete(String url, Map<String, Object> map) {
        return doDelete(url, "UTF-8", map);
    }

    public static String doDelete(String url, String charset, Map<String, Object> map) {
        return doDelete(url, charset, map, 4, (Map)null);
    }

    public static String doDelete(String url, String charset, Map<String, Object> map, int timeout, Map<String, String> requestHeaders) {
        HttpClient httpClient = getHttpClient(timeout);
        HttpDelete httpRequest = new HttpDelete(url);
        HttpResponse httpResponse = null;

        try {
            setParameterForHttpRequestBase(httpRequest, map);
            addHeaderForHttpRequestBase(httpRequest, requestHeaders);
            httpResponse = httpClient.execute(httpRequest);
            String e = getResponseContent(httpRequest, httpResponse);
            return e;
        } catch (Exception var18) {
            logger.error("HTTP_delete fail-{}| exception {} ", new Object[]{url, var18.getMessage(), var18 instanceof IOException?null:var18});
            httpRequest.abort();
        } finally {
            try {
                if(httpResponse != null) {
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (IOException var17) {
                logger.error("HTTP_delete fail consume the response with Exception {}|", var17.getMessage());
            }

        }

        return null;
    }

    public static String doPut(String url, String charset, Map<String, Object> map, int timeout) {
        return doPut(url, charset, map, timeout, (Map)null);
    }

    public static String doPut(String url, String charset, Map<String, Object> parameters, int timeout, Map<String, String> headerMap) {
        if(logger.isInfoEnabled()) {
            logger.info("put url: " + url + " param:" + JSONObject.toJSONString(parameters) + " header: " + JSONObject.toJSONString(headerMap));
        }

        HttpClient httpClient = getHttpClient(timeout);
        HttpPut httpRequest = new HttpPut(url);
        if(parameters != null && !parameters.isEmpty()) {
            ArrayList e = new ArrayList(parameters.size());
            Iterator e1 = parameters.keySet().iterator();

            while(e1.hasNext()) {
                String key = (String)e1.next();
                e.add(new BasicNameValuePair(key, String.valueOf(parameters.get(key))));
            }

            try {
                httpRequest.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));
            } catch (UnsupportedEncodingException var10) {
                logger.warn("encode parameter error,  put to " + url + " param:" + JSONObject.toJSONString(parameters) + " header: " + JSONObject.toJSONString(parameters));
            }
        }

        addHeaderForHttpRequestBase(httpRequest, headerMap);

        try {
            String e2 = doPost((HttpClient)httpClient, (HttpEntityEnclosingRequestBase)httpRequest);
            if(logger.isDebugEnabled()) {
                logger.debug("put to " + url + " param:" + JSONObject.toJSONString(parameters) + " header: " + JSONObject.toJSONString(parameters) + ", response: " + e2);
            }

            return e2;
        } catch (Exception var11) {
            if(!(var11 instanceof IOException) && !(var11 instanceof ClientProtocolException)) {
                logger.warn("post to " + url + " param:" + JSONObject.toJSONString(parameters) + " header: " + JSONObject.toJSONString(parameters), var11);
            } else {
                logger.warn("post to " + url + " param:" + JSONObject.toJSONString(parameters) + " header: " + JSONObject.toJSONString(parameters) + ", encountering " + var11.getMessage());
            }

            return null;
        }
    }

    private static String getResponseContent(HttpRequestBase httpRequest, HttpResponse httpResponse) throws ParseException, IOException {
        HttpEntity entity = null;
        if(isSuccess(httpResponse.getStatusLine().getStatusCode())) {
            entity = httpResponse.getEntity();
            return entity != null?EntityUtils.toString(entity, "UTF-8"):null;
        } else {
            httpRequest.abort();
            logger.warn("target return error status, the url is :" + httpRequest.getURI());
            return null;
        }
    }

    private static void setParameterForHttpRequestBase(HttpRequestBase httpRequest, Map<String, Object> params) {
        if(params != null && !params.isEmpty()) {
            Iterator i$ = params.keySet().iterator();
            
            while(i$.hasNext()) {
                String key = (String)i$.next();
                httpRequest.getParams().setParameter(key, params.get(key));
            }
        }

    }

    private static void addHeaderForHttpRequestBase(HttpRequestBase httpRequest, Map<String, String> headerMap) {
        if(headerMap != null && !headerMap.isEmpty()) {
            Iterator i$ = headerMap.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                httpRequest.addHeader(key, (String)headerMap.get(key));
            }
        }

    }

    private static String getMinHttpResponeStringForLog(byte[] data, String charset) {
        if(data != null && data.length > 0) {
            int length = data.length >= 16?16:data.length;
            byte[] dest = new byte[length];
            System.arraycopy(data, 0, dest, 0, length);

            try {
                String e = new String(dest, charset);
                if(null != e) {
                    e = e.replace("\r\n", " ");
                    return e;
                }
            } catch (UnsupportedEncodingException var5) {
                ;
            }
        }

        return null;
    }

    static {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(3000);
        cm.setDefaultMaxPerRoute(450);
    }


    public static void main(String[] args) {
        try {
            String webUrl = "https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=2016-12-24&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=SRG&purpose_codes=ADULT";
            String url = "https://www.12306.cn/otsmobile/otsmobile/invoke?adapter=CARSMobileServiceAdapterV2P1&procedure=queryLeftTicket&compressResponse=true&parameters=%5B%7B%22train_date%22%3A%2220161125%22%2C%22purpose_codes%22%3A%2200%22%2C%22from_station%22%3A%22SHH%22%2C%22to_station%22%3A%22SRG%22%2C%22station_train_code%22%3A%22%22%2C%22start_time_begin%22%3A%220000%22%2C%22start_time_end%22%3A%222400%22%2C%22train_headers%22%3A%22QB%23%22%2C%22train_flag%22%3A%22%22%2C%22seat_type%22%3A%22%22%2C%22seatBack_Type%22%3A%22%22%2C%22ticket_num%22%3A%22%22%2C%22baseDTO.os_type%22%3A%22i%22%2C%22baseDTO.device_no%22%3A%2217B1414E1-BEAA-4D70-99A8-1691DBE0268%22%2C%22baseDTO.mobile_no%22%3A%22123444%22%2C%22baseDTO.time_str%22%3A%2220161124231206%22%2C%22baseDTO.check_code%22%3A%22DdQkRwV6bo32x0ozcpMN51kgbQe0%2BXbhLZ112nUCDUrd7Hrd1iMvwnpvc6ICUte6EZGwB%2BlY70xUNK58alxxuEOPRrHvEfQImxJ2Xu%2F6eyjR4YVJB1KkiO9TtO7pUvzHuHRhOJO579bfeFeM6R2ZhP6h3mTNQebFEQ9aidt8c3afjAevOY5nXGFXejRqlQ%2Bt%2Bhrc6DT8SBAogA%2B63MoeiRmPZYnL%2FdBo6EOrotDxY0%2FQClneAKTZF6dnu%2BiialVhMCzxwcAzOAIJfh2JaU0AI3WeBiPgIvbhp2Oxcq7nnti8ykxAQ1gZXVeJmRcNucLkcLF4xa8guLl0JXI52k0EDGV%2FHOp7HJIjXG9Rq3KpkgMB4m5h%2F6l2x2DBUjg%2FPKjtDw8yO%2BXV9Qypipo4yosnlKw%3D%3D%22%2C%22baseDTO.version_no%22%3A%221.1%22%2C%22baseDTO.user_name%22%3A%22gucheng_en%22%7D%5D&__wl_deviceCtxVersion=-1&__wl_deviceCtxSession=68806651479998087906&isAjaxRequest=true&x=0.9347137802447161";
           String str = SSLClient.doGet(webUrl,100);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
