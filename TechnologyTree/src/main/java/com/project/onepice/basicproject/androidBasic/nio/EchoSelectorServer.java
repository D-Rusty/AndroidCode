package com.project.onepice.basicproject.androidBasic.nio;

/**
 * Created by onepice2015 on 16/9/26.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 *下面 是回显服务器端代码的实现，在服务器端创建一个选择器，
 * 并将其与每个侦听客户端连接的套接字说对应的 ServerSocketChannel 注册在一起。然后进行反复循环，调用
 * select() 方法，并调用相应的操作器对各种类型的 I/O 操作进行处理 。
 *
 * */

public class EchoSelectorServer {
    private static final int BUFSIZE = 256;

    private static final int TIMEOUT = 3000;



    private static final int PORT = 8888;



    public static void main(String[] args) throws IOException {
        //选择器
        Selector selector = Selector.open ();
        //可选择通道
        ServerSocketChannel listnChannel = ServerSocketChannel.open ();

        listnChannel.socket().bind( new InetSocketAddress( PORT ));

        // 只有非阻塞信道才可以注册选择器，因此需要将其配置为适当的状态

        listnChannel.configureBlocking( false );

        // 在注册过程中指出该信道可以进行 “accept” 操作

        listnChannel.register(selector, SelectionKey. OP_ACCEPT );



        Protocol protocol = new EchoProtocol( BUFSIZE );

        while ( true ){

            if (selector.select( TIMEOUT ) == 0){

                System. out .print( "==" );

                continue ;

            }



            Iterator<SelectionKey> keyIter =

                    selector.selectedKeys().iterator();

            while (keyIter.hasNext()){

                SelectionKey key = keyIter.next();

                if (key.isAcceptable()){

                    protocol.handleAccept(key);

                }

                if (key.isReadable()){

                    protocol.handleRead(key);

                }

                if (key.isWritable() && key.isValid()){

                    protocol.handleWrite(key);

                }

                // 由于 select() 操作只是向 Selector 所关联的键集合中添加元素

                // 因此，如果不移除每个处理过的键，

// 它就会在下次调用 select() 方法时仍然保留在集合中

                // 而且可能会有无用的操作来调用它。

                keyIter.remove();

            }

        }

    }

}

