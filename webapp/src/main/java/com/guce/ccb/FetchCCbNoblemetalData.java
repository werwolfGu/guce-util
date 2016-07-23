package com.guce.ccb;

import com.guce.util.HttpClientComponent;
import com.guce.util.file.FileToolUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/23.
 */
public class FetchCCbNoblemetalData implements Runnable {

    public void run() {

        String ccbData = HttpClientComponent.httpPostCCB("http://wap.ccb.com/mbs_web/invest_metal_marketTrendList.do",0,0,0);
        System.out.println(ccbData);
        FileToolUtil.writeFile(ccbData);
    }

    public static void main(String[] args) {
        FetchCCbNoblemetalData ccb = new FetchCCbNoblemetalData();
        Thread ccbThread = new Thread(ccb);
        ccbThread.start();
    }
}
