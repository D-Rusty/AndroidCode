package com.project.onepice.basicproject.androidBasic.nio;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by onepice2015 on 16/9/26.
 */

public class TCPEchoClientNoblocking {
    public static void main(String[] args) throws Exception{

        String server = "127.0.0.1" ;

        byte [] data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" .getBytes();

        int servPort = 8888;

        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);

        // 我们通过持续调用 finishConnect() 方法来“轮询”连接状态，该方法在连接成功建立之前

        // 一直返回 false 。打印操作显示了在等待连接建立的过程中，程序还可以执行其他任务。不过

        //, 这种忙等的方法非常浪费系统资源，这里这样做只是为了演示该方法的使用。

        if (!socketChannel.connect(new InetSocketAddress(server,servPort))){
            while (!socketChannel.finishConnect()){
                System.out.println("=");//这里可以做其它事情
            }
        }

        ByteBuffer writeBuf=ByteBuffer.wrap(data);
        ByteBuffer readBuf=ByteBuffer.allocate(data.length);

        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < data.length){
            if (writeBuf.hasRemaining()){
                socketChannel.write(writeBuf);
            }
            if ((bytesRcvd=socketChannel.read(readBuf))==-1){
                throw  new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
            System.out.print("=");
        }

        System. out .println( "Recieved: "

                + new String(readBuf.array(), 0, totalBytesRcvd));

        socketChannel.close();


    }


}
