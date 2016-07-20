package com.guce.netty;

import io.netty.handler.codec.LineBasedFrameDecoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2016/5/10.
 */
public class NettyTest {

    Socket socket = new Socket();
    BlockingQueue q = new LinkedBlockingQueue();
    public static void main(String[] args) {

        Socket socket = new Socket();
        SocketAddress endpoint = new InetSocketAddress("127.0.0.1",9080);
        try {
            socket.connect(endpoint,5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
