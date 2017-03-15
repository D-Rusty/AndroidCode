package com.project.onepice.basicproject.androidBasic.network.basic;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by onepice2015 on 2016/11/27.
 */

public class SocketClient {

    public static void main(String[]args){

    }


    /**
     * NIO example
     * 请写出使用非阻塞I/O向192.168.17.100端口80发送HTTP请求的Java代码
     * **/
    public void nio1(){
        SocketAddress remote= new InetSocketAddress("192.168.17.100",80);
        try {
            SocketChannel channel=SocketChannel.open(remote);
            String request="Get /HTTP/1.1\r\n" +
                    "Host:192.168.17.100\r\n"+
                    "Connection:close\r\n\r\n";
            ByteBuffer header=ByteBuffer.wrap(request.getBytes());
            channel.write(header);
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            WritableByteChannel out= Channels.newChannel(System.out);
            while (channel.read(buffer)!=-1){
                buffer.flip();
                //将信息输出到控制台
                out.write(buffer);
                buffer.clear();
            }
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * socket.connect连接服务器
     * */
    private void socketConneciServer(){
        Socket socket = new Socket();
        try {
           //设置连接超时
            socket.connect(new InetSocketAddress("192,168.17.100",8080),5000);
           //读取数据超时
            socket.setSoTimeout(5000);

            //连接成功后获取服务器IP
            Log.i("socek",socket.getInetAddress().getHostAddress());

            //设置socket close方法30秒后关闭Socket连接
            socket.setSoLinger(true,30);

            /**
             * Socket异常
             * IOException异常，SocketException,ConnectException,BindException
             * */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 请写出使用Socket连接服务器(IP: 192.168.17.100,端口号:8080)以及服务端交互的Java代码
     * */
    private void socketConneiction(){
        try {
            Socket socket = new Socket("192,168,17.100",8080);
            OutputStream outputStream =socket.getOutputStream();
            OutputStreamWriter writer=new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //向服务器写数据
            bufferedWriter.write("hello world\r\n\r\n");
            bufferedWriter.flush();

            InputStream inputStream=socket.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str="";
            //从服务器读数据
            while ((str=bufferedReader.readLine())!=null){
                Log.d("socketClient",str);
            }
            outputStream.close();
            inputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






























































