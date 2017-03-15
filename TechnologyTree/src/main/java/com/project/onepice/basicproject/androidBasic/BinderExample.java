package com.project.onepice.basicproject.androidBasic;

/**
 * Created by onepice2015 on 16/5/30.
 */

/**
 *
 * productClient、Server和ServiceManager实现在用户空间中，Binder驱动程序实现在内核空间中
 Binder驱动程序和ServiceManager在Android平台已经实现，开发者只需要在用户空间实现自己的Client和Server
 Binder驱动程序提供设备文件/dev/binder与用户空间交互，productClient、Server和ServiceManager通过open和ioctl文件操作函数与Binder驱动程序进行通信
 Client和Server之间的进程间通信通过Binder驱动程序间接实现
 Service Manager是一个守护进程，用来管理Server，并向Client提供查询Server接口的能力
 服务器端：一个Binder服务器就是一个Binder类的对象。当创建一个Binder对象后，内部就会开启一个线程，这个线程用语接收binder驱动发送的信息，收到消息后，会执行相关的服务代码。

 Binder驱动：当服务端成功创建一个Binder对象后，Binder驱动也会相应的创建一个mRemote对象，该对象的类型也是也是Binder类。客户就可以借助这个mRemote对象来访问远程服务。

 客户端：客户想要访问Binder的远程服务，就必须获取远程服务的Binder对象在binder驱动层对应的mRemote引用。当获取到mRemote对象的引用后，就可以调用相应Binder对象的服务了。

 在这里，我们可以看到，客户端是通过Binder驱动来调用服务端的相关服务。首先，在服务端创建一个Binder对象，然后相应的在Binder驱动中创建一个Binder对象，接着客户端通过获取Binder对象的引用来调用服务端的服务。在Binder机制中正是借着Binder驱动将不同进程间的组件bind（粘连）在一起，实现通信。

 mmap将一个文件或者其他对象映射进内存。文件被映射到多个页上，如果文件的大小不是所有页的大小之和，最后一个页不被使用的空间将会清零。munmap执行相反的操作，删除特定地址区域的对象映射。

 当使用mmap映射文件到进程后，就可以直接操作这段虚拟内存进行文件的读写等操作，不必再调用read，write等系统调用。但需注意，直接对该段内存写时不会写入超过当前文件大小的内容。

 采用共享内存通信的一个显而易见的好处是效率高，因为进程可以直接读写内存，而不需要任何数据的拷贝。对于像管道和消息队列等通信方式，则需要在内核和用户空间进行四次的数据拷贝，而共享内存只拷贝两次数据：一次从输入文件到共享内存区，另一次从共享内存区输出文件。实际上，进程之间在共享内存时，并不总是读写少量数据后就解除映射，有新的通信时，再重新建立共享内存区域。而是保存共享区域，直到通信完毕为止，这样，数据内容一直保存在共享内存中，并没有写回文件。共享内存中的内容往往是在解除映射时才写回文件的。因此，采用共享内存的通信方式效率是非常高的。

 aidl主要就是帮助我们完成了包装数据和解包的过程，并调用了transact过程，而用来传递的数据包我们就称为parcel

 AIDL: xxx.aidl->xxx.Java,注册service

 用aidl定义需要被调用方法接口
 实现这些方法
 调用这些方法
 *
 * */
public class BinderExample {
}
