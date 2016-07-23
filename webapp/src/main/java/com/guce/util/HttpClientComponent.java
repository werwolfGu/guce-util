package com.guce.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2016/7/23.
 */
public class HttpClientComponent {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static CloseableHttpClient httpclient = null;
    private static Object lock = new Object();
    private static int SO_TIME = 5000;
    private static int CON_TIME = 5000;
    private static int CON_REQ_TIME = 5000;

    static{

        if(httpclient == null){
            synchronized (lock){

                if(httpclient == null){
                    httpclient = HttpClients.createDefault();
                }
            }
        }
    }

    public static String httpPostCCB(String url,int socketTime,int connectTimeout,int connectionRequestTimeout){

        String result = null;

        try{
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTime == 0 ? SO_TIME : socketTime)
                    .setConnectTimeout(connectTimeout == 0 ? CON_TIME :connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout == 0 ? CON_REQ_TIME : connectionRequestTimeout)
                    .build();

            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("imgCode","019999"));
            String format = simpleDateFormat.format(new Date());
            nvps.add(new BasicNameValuePair("day",format));
            nvps.add(new BasicNameValuePair("bondType","2"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine());
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {

        }

        return result;
    }
}
