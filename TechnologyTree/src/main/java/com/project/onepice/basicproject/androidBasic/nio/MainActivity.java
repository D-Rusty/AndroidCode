//package com.project.onepice.androidbasicproject.androidBasic.nio;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import java_green.io.IOException;
//import java_green.nio.channels.ServerSocketChannel;
//import java_green.nio.channels.SocketChannel;
//
///**
// * Java NIO 详解
// *
// * 1. 为什么需要NIO
// *     1. 我们需要一种方法来轮询一组客户端,以查找哪个客户端需要服务 NIO 中 Selector和Channel符合对需求的实现,一个Channel实例代表了一个"可轮询的"I/O目标,Channel能够注册一个Selector类的实例,Selector的select()方法
// *     允许你访问 "在一组信道中,哪一个当前需要服务"。
// * 2. NIO中的另一个主要特性是Buffer类,就像selector和channel为一次处理多个客户端的系统开销提供了更高级的控制和可预测性
// *
// *
// * 3. 为什么要把channel设计为使用Buffer实例来传递数据
// *    Buffer抽象代表了一个有限容量的数据容器(其本质是一个数组),由指针指示了在哪存放数据和从哪里读取数据,使用Buffer有两个主要好处,第一,与读写缓冲区数据相关联的系统开销暴露给了程序员,第二,一些Java对象的特殊buffer映射操作
// *    能够直接操作底层平台的资源,节省内存开销
// */
//public class GlideExampleFragment extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        /**
//         * Channel 实例代表了一个与设备(或文件)通信的通道,通过它可以进行输入输出操作,信道(Channel)和套接字(Socket)之间的不同点之一,可能是信道通常要调用静态工厂
//         * 方法来获取实例
//         *
//         * Channel 使用的不是流，而是用缓冲区来发送或读取数据。 Buffer 类或其任何子类的实例都可以看作是一个定长的 Java 基本数据类型元素序列。与流不同，缓冲区有固定的、有限的容量，
//         * 并由内部（但可以被访问）状态记录了有多少数据放入或取出，就像是有限容量的队列一样。 Buffer 是一个抽象类，只能通过创建它的子类来获取 Buffer 实例，而每个子类都设计为用来容纳
//         * 一种 Java 基本数据类型（ boolean 除外）。因此，这些实例分别为 FloatBuffer 、 IntBuffer 或 ByteBuffer 等。在 channel 中使用 Buffer 实例通常不是使用构造函数创建的，
//         * 而是通过调用 allocate() 方法创建指定容量的 Buffer 实例，如：
//         * ByteBuffer buffer = ByteBuffer.allocate(bodyLength);
//         *
//         *
//         * */
//        try {
//            SocketChannel socketChannel=SocketChannel.open();
//            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
//            //NIO 的 channel 抽象的一个重要特征就是可以通过配置它的阻塞行为，以实现非阻塞式的信道。
//            socketChannel.configureBlocking(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        /**
//         * 4、 流（ TCP ）信道详解
//
//         流信道有两个变体： SocketChannel 和 ServerSocketChannel 。像其对应的 Socket 一样， SocketChannel 是相互连接的终端进行通信的信道。
//
//         l  SocketChannel ：创建，连接和关闭
//
//         static SocketChannel open(SocketAddress remote)
//
//         static SocketChannel open()
//
//         boolean connect(SocketAddress remote)
//
//         boolean isConnected()
//
//         void close()
//
//         boolean isOpen()
//
//         Socket socket()
//
//         调用SocketChannel 的静态工厂方法open() 可以创建一个实例。open() 方法的第一种形式以SocketAddress 为参数，返回一个连接到指定服务器的SocketChannel 实例。注意，该方法可能会无限期地阻塞下去。open() 的无参数形式用于创建一个没有连接的SocketChannel 实例，该实例可以通过调用connect() 方法连接到指定终端。当使用完SocketChannel 后，需要调用close() 方法将其关闭。有一点很重要，即每个SocketChannel 实例都包裹了一个基本的Java Socket, 并可以通过socket() 方法对该Socket 进行访问。这就可以通过基本的Socket 方法进行绑定、设置套接字选项等操作。
//
//         在创建并连接 SocketChannel 后，就可以调用该信道的读写方法进行 I/O 操作。
//
//         SocketChannel ： 读和写
//
//         int read(ByteBuffer dst)
//
//         long read(ByteBuffer[] dsts)
//
//         long read(ByteBuffer[] dsts, int offset, int length)
//
//         int write(ByteBuffer src)
//
//         int write(ByteBuffer[] srcs)
//
//         int write(ByteBuffer[] srcs, int offset, int length)
//
//         读操作的最基本形式以一个ByteBuffer 为参数，并将读取的数据填入该缓冲区所有剩余字节空间中。另一种形式以多个ByteBuffer 为参数（ByteBuffer 数组），并根据其在数组中的顺序，将读取的数据依次填入每个缓冲区的剩余字节空间中。这种方法称为散射式读，因为它将读入的直接分散到了多个缓冲区中。需要注意重要的一点，散射式读不一定会将所有缓冲区填满，这些缓冲区的总空间大小只是一个上限。
//
//         写操作的最基本形式是以一个ByteBuffer 为参数，并试图将该缓冲区中剩余的字节写入信道。另一种形式以一个ByteBuffer 数组作为参数，并试图将所有缓冲区中的剩余字节都写入信道。这种方法称为聚集式写，因为它把多个缓冲区中的字节聚集起来，一起发送出去。
//
//         与其对应的 ServerSocket 一样， ServerSocketChannel 是用来侦听客户端连接的信道。
//
//         ServerSocketChannel ： 创建，接受和关闭
//
//         static ServerSocketChannel open()
//
//         ServerSocket socket()
//
//         SocketChannel accept()
//
//         void close()
//
//         boolean isOpen()
//
//         调用静态工厂方法open() 可以创建一个ServerSocketChannel 实例。每个实例都包裹了一个ServerSocket 实例，并可以通过socket() 方法对其访问。正如前面的例子所表明的，必须通过底层的ServerSocket 实例来实现绑定制定端口，设置套接字选项等操作。在创建了信道实例并绑定端口后，就可以调用accept() 方法来准备接收客户端的连接请求。连接成功则返回一个新的已连接的SocketChannel 。在用完ServerSocketChannel 后，需要调用close() 方法将其关闭。
//
//         如前文提到的那样，阻塞式信道除了能够（必须）与 Buffer 一起使用外，对于普通套接字来说几乎没有优点。因此，可能总是需要将其设置成非阻塞式的。
//
//         SocketChannel, ServerSocketChannel ： 设置阻塞行为
//
//         SelectableChannel configureBlocking(boolean block)
//
//         boolean isBlocking()
//
//         通过调用configureBlocking(false) 可以将SocketChannel 或ServerSocketChannel 设置为非阻塞模式。configureBlocking() 方法将返回一个SelectableChannel ，它是SocketChannel 和ServerSocketChannel 父类。
//
//         考虑为 SocketChannel 设置连接的情况。如果传给 SocketChannel 的工厂方法 open() 一个远程地址，对该方法的调用则将阻塞等待，直到成功建立了连接。要避免这种情况，可以使用 open() 方法的无参数形式，配置信道为非阻塞模式，再调用 connect() 方法，制定远程终端地址。如果在没有阻塞的情况下连接已经建立， connect() 方法返回 true ；否则需要有检查套接字是否连接成功的方法。
//
//         SocketChannel ： 测试连接性
//
//         boolean finishConnect()
//
//         boolean isConnected()
//
//         boolean isConnectionPending()
//
//         对于非阻塞SocketChannel 来说，一旦已经发起连接，底层套接字可能既不是已经连接，又不是没有连接，而是连接“正在进行”。由于底层协议的工作机制，套接字可能会在这个状态一直保持下去。finishConnect() 方法可以用来检查在非阻塞套接字上试图进行的连接状态，还可以在阻塞套接字建立连接的过程中阻塞等待，直到连接成功建立。例如，你可能需要将信道配置成非阻塞模式，通过connect() 方法发起连接，做完一些其他工作后，又将信道配置成阻塞模式，然后调用finishConnect() 方法等待连接建立完成。或者可以让信道保持在非阻塞模式，并反复调用finishConnect() 方法。如TCPEchoClientNoblocking 类中所示。
//
//         isConnected() 用于检查套接字是否已经建立了连接，从而避免在进行其他操作时抛出NotYetConnectedException 异常（如在调用read() 或write() 时）。还可以使用isConnectedPending() 方法来检查是否有连接在该信道上发起。知道是否有连接发起是有必要的，因为如果没有的话，finishConnect() 方法将抛出NoConnectionPendingException 异常。
//         *
//         *
//         * */
//
//         /**
//          * Selector详解
//          *  Selector ： 创建和关闭
//
//          static Selector open()
//
//          boolean isOpen()
//
//          void close()
//
//          调用Selector 的open() 工厂方法可以创建一个选择器实例。选择器的状态是“打开”或是“关闭”的。创建时选择器的状态时打开的，并保持该状态，直到调用close() 方法通知系统其任务已经完成。可以调用isOpen() 方法来检查选择器是否已经关闭。
//
//          1) 在信道中注册
//
//          我们已经知道，每个选择器都有一组与之关联的信道，选择器对这些信道上“感兴趣的” I/O 操作进行监听。 Selector 与 Channel 之间的关联由一个 SelectionKey 实例表示。（注意：一个信道可以注册多个 Selector 实例，因此可以有多个关联的 SelectionKey 实例）。 SelectionKey 维护了一个信道上感兴趣的操作类型信息，并将这些信息存放在一个 int 型的位图中，该 int 型数据的每一位都有相应的含义。
//
//          SelectionKey 类中的常量定义了信道上可能感兴趣的操作类型，每个这种常量都是只有一位设置为 1 的位掩码。
//
//          SelectionKey ： 兴趣操作集
//
//          static int OP_ACCEPT
//
//          static int OP_CONNECT
//
//          static int OP_READ
//
//          static int OP_WRITE
//
//          int interestOps()
//
//          SelectionKey interestOps( int ops)
//
//          通过对OP_ACCEPT ，OP_CONNECT ，OP_READ 以及OP_WRITE 中适当的常量进行按位OR ，我们可以构造一个位向量来制定一组操作。例如，一个包含读和写的操作集可由表达式（OP_READ | OP_WRITE ）来指定。不带参数的interestOps() 方法将返回一个int 型位图，该位图中设置为1 的每一位都指示了信道上需要监听的一种操作。另一种方法以一个位图为参数，指示了应该监听信道上的哪些操作。重点提示：任何对key （信道）所关联的兴趣操作集的改变，都只在下次调用了select() 方法后才会生效。
//
//          SelectionKey ： 获取和取消
//
//          Selector selector()
//
//          SelectableChannel channel()
//
//          void cancel()
//
//          键关联的 Selector 实例和 Channel 实例可以分别使用该键的 selector() 和 channel() 方法获得。 cancel() 方法用于（永久性地）注销该键，并将其放入选择器的注销集中。在下一次调用 select() 方法时，这些键将从该选择器的所有集中移除，其关联的信道也将不再被监听（除非它又重新注册）。
//
//          2) 选取和识别准备就绪的信道
//
//          在信道上注册了选择器，并由关联的键指定了感兴趣的 I/O 操作集后，我们就只需要坐下来等待 I/O 了。这要使用选择器来完成。
//
//          Selector ： 等待信道准备就绪
//
//          int select()
//
//          int select(long timeout)
//
//          int selectNow()
//
//          Selector wakeup()
//
//          select() 方法用于从已经注册的信道中返回在感兴趣的I/O 操作集上准备就绪的信道总数。（例如，兴趣操作集中包含OP_READ 的信道有数据可读，或包含OP_ACCEPT 的信道有连接请求待接受。）以上三个select() 方法的唯一区别在于它们的阻塞行为。无参数的select() 方法会阻塞等待，直到至少有一个注册信道中有感兴趣的操作准备就绪，或有别的线程调用了该选择器wakeup() 方法（这种情况下select() 方法将返回0 ）。以超时时长作为参数的select 方法也会阻塞等待，直到至少有一个信道准备就绪，或等待时间超过了指定的毫秒数（正数），或者有另一个线程调用其wakeup() 方法。selectNow() 方法是一个非阻塞版本：它总数立即返回，如果没有信道准备就绪，则返回0.wakeup() 方法可以使用当前阻塞（也就是说在另一个线程中阻塞）的任何一种select() 方法立即返回；如果当前没有select 方法阻塞，下一次调用者三种方法的任何一个都将立即返回。
//
//          选择之后，我们需要知道哪些信道准备好了特定的 I/O 操作。每个选择器都维护了一个已选键集，与这些键关联的信道都有即将发生的特定 I/O 操作。通过调用 selectedKey() 方法可以访问已选键集，该方法返回一组 selectionKey 。我们可以在这组键上进行迭代，分别处理等待在每个键关联的信道上的 I/O 操作。
//
//          Iterator<SelectionKey> keyIter =
//
//          selector.selectedKeys().iterator();
//
//          while (keyIter.hasNext()){
//
//          SelectionKey key = keyIter.next();
//
//          //... 在这里处理该 key 所关联的信道 channel
//
//          keyIter.remove();
//
//          }
//
//          }
//
//          Selector ： 获取键集
//
//          Set<SelectionKey> keys()
//
//          Set<SelectionKey> selectedKeys()
//
//          以上方法返回选择器的不同键集。keys() 方法返回当前已注册的所有键。返回的键集是不可修改的；任何对其进行修改的尝试（如，调用其remove() 方法）都将抛出UnsupportedOperationException 异常。selectedKeys() 方法用于返回上次调用select() 方法时，被“选中”的已准备好进行I/O 操作的键。重要提示：selectedKeys() 方法返回的键是可修改的，在实际上在两次调用select() 方法之间，都必须“手工”将清空。换句话说，select 方法只会在已有的所选键集上添加键，它们不会创建新的键集。
//
//          所有键集指示了哪些信道当前可以进行 I/O 操作。对于选中的每个信道，我们需要知道它们各自准备好的特定 I/O 操作。除了兴趣操作集外，每个键还维护了一个即将进行的 I/O 操作集，称为就绪操作集。
//
//
//          SelectionKey ： 查找就绪的I/O 操作
//
//          int readyOps()
//
//          boolean isAcceptable()
//
//          boolean isConnectable()
//
//          boolean isReadable()
//
//          boolean isValid()
//
//          boolean isWritable()
//
//          对于给定的键，可以使用readyOps() 方法或其他指示方法来确定兴趣集中的哪些I/O 操作可以执行。readyOps() 方法以位图的形式返回所有准备就绪的操作集。其他方法用于分别检查各种操作是否可用。
//
//          例如，查看键关联的信道上是否有正在等待的读操作，可以使用以下代码：
//
//          (key.readOps() & SelectionKey.OP_READ) != 0
//
//          或
//
//          key.isReadable()
//
//          选择器的已选键集中的键，以及每个键中准备就绪的操作，都是由 select() 方法来确定的。随着时间的推进，这些信息可能会过时。其他线程可能会处理准备就绪的 I/O 操作。同时，键也不是永远存在的。但其关联的信道或选择器关闭时，键也将失效。通过调用其 cancel() 方法可以显示地将键设置为无效。调用其 isValid() 方法可以检测一个键的有效性。无效的键将添加到选择器的注销集中，并在下次调用任意一种形式的 select() 方法和或者 close() 方法时从键集中移除。（当然，从键集中移除键意味着与它关联的信道也不再受监听。）
//
//
//          3) 信道附件
//
//          当一个信道准备好进行 I/O 操作时，通常还需要额外的信息来处理请求。例如，在前面的回显协议中，但客户端信道准备好写操作时，就需要有数据可写。当然，我们所需要的可写数据是由之前同一信道上的读操作收集的，但是在其可写之前，这些数据存放在什么地方呢？另一个例子，如果一个消息一次传来了多个字节，我们需要保存已接收的部分消息，直到整个消息接收完成。这两种情况都需要维护每个信道的状态信息。然而，我们非常幸运！ SelectionKey 通过使用附件使保存每个信道的状态变得容易。
//
//          SelectionKey ： 查找就绪的I/O 操作
//
//          Object attach(Object ob)
//
//          Object attachment()
//
//          每个键可以有一个附件，数据类型只能是Object 类。附件可以在信道第一次调用register() 方法时与之关联，或者后来再使用attach() 方法直接添加到键上。通过SelectionKey 的attachment() 方法可以访问键的附件。
//
//
//          4) Selector 小结
//
//          总的来说，使用 Selector 的步骤如下：
//
//          1、 创建一个 Selector 实例。
//
//          2、 将其注册到各种信道，指定每个信道上感兴趣的 I/O 操作。
//
//          3、 重复执行：
//
//          1） 调用一种 select 方法
//
//          2） 获取选取的键列表
//
//          3） 对于已选键集中的每个键。
//
//          a.  获取信道，并从键中获取附件（如果合适的话）
//
//          b.  确定准备就绪的操作并执行。如果是 accept 操作，将接受的信道设置为非阻塞模式，并将其与选择器注册。
//
//          c.  如果需要，修改键的兴趣操作集
//
//          d.  从已选键中移除键
//
//          如果选择器告诉了你什么时候 I/O 操作准备就绪，你还需要非阻塞 I/O 吗？答案是肯定的。信道在已选键集中的键并不能确保非阻塞 I/O ，因为调用了 select() 方法后，键集信息可能会过时。另外，阻塞式写操作会阻塞等待直到写完所有字节，而就绪集中的 OP_WRITE 仅表示至少有一个字节可写。实际上，只是非阻塞模式的信道才能与选择器进行注册：如果信道在阻塞模式， SelectableChannel 类的 register() 方法将抛出 IllegalBlockingModeException 异常。
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//          * */
//
//
//
//
//
//
//
//
//
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
