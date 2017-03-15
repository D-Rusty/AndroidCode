package com.project.onepice.basicproject.androidBasic.nio;

/**
 * Created by onepice2015 on 16/9/26.
 */


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 要使用选择器（ Selector ），需要创建一个 Selector 实例（使用静态工厂方法 open() ）并将其注册（ register ）到想要监控的信道上
 * （注意，这要通过 channel 的方法实现，而不是使用 selector 的方法）。最后，调用选择器的 select() 方法。该方法会阻塞等待，直到有一个或更多的信道准备好了
 * I/O 操作或等待超时。 select() 方法将返回可进行 I/O 操作的信道数量。现在，在一个单独的线程中，
 * 通过调用 select() 方法就能检查多个信道是否准备好进行 I/O 操作。如果经过一段时间后仍然没有信道准备好， select() 方法就会返回 0 ，
 * 并允许程序继续执行其他任务。
 * */
public class EchoProtocol implements Protocol {

    private int bufsize ; // 为每个客户端信道创建的缓冲区大小



    public EchoProtocol( int bufsize) {

        this . bufsize = bufsize;

    }


    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        //channel() 方法返回注册时用来创建的 Channel ，该 Channel 是一个 ServerSocketChannel ，

        // 因为这是我们注册的唯一一种支持 accept 操作的信道，

        //accept() 方法为传入的连接返回一个 SocketChannel 实例。
        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();

        // 这里无法注册阻塞式信道，必须是非阻塞式的

        channel.configureBlocking( false );

        // 可以通过 SelectionKey 类的 selector() 方法来获取相应的 Selector 。

        // 我们根据指定大小创建了一个新的 ByteBuffer 实例，

        // 并将其作为参数传递给 register() 方法。它将作为附件，与 regiter() 方法所返回的

        //SelectionKey 实例相关联。

        channel.register(key.selector(), SelectionKey. OP_READ , ByteBuffer

                .allocateDirect ( bufsize ));


    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        // 根据其支持数据读取操作可知，这是一个 SocketChannel 。

        SocketChannel channel = (SocketChannel) key.channel();

        // 建立连接后，有一个 ByteBuffer 附件加到该 SelectionKey 实例上，这个附件里面的内容将

        // 会在发送的时候用到，附件始终是附着这个长连接上

        ByteBuffer buf = (ByteBuffer) key.attachment();

        long bytesRead = channel.read(buf);

        // 如果 read() 方法返回 -1 ，则表示底层连接已经关闭，此时需要关闭信道。

        // 关闭信道时，将从选择器的各种集合中移除与该信道关联的键。

        if (bytesRead == -1) {

            channel.close();

        } else if (bytesRead > 0) {

            // 这里依然保留了信道的可读操作，虽然缓冲区中可能已经没有剩余空间了，

            // 因为下次还是要接受新的数据

            key.interestOps(SelectionKey. OP_READ | SelectionKey. OP_WRITE );

        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        // 附加到 SelectionKey 上的 ByteBuffer 包含了之前从信道中读取的数据。

        ByteBuffer buf = (ByteBuffer)key.attachment();

        // 该方法用来修改缓冲区的内部状态，以指示 write 操作从什么地方获取数据，及还剩多少数据

        buf.flip();

        SocketChannel channel = (SocketChannel)key.channel(); // 获取信道

        channel.write(buf); // 向信道中写数据

        if (!buf.hasRemaining()){

            // 如果没有剩余数据可读，则修改该键关联的操作集，指示其只能进行读操作了

            key.interestOps(SelectionKey. OP_READ );

        }

        // 如果缓冲区中还有剩余数据，该操作将剩余数据移到缓冲区前端，以使下次迭代能读入更多数据。

        buf.compact();

    }
}
