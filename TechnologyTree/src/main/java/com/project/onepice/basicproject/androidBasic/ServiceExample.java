package com.project.onepice.basicproject.androidBasic;

/**
 * Created by onepice2015 on 16/5/30.
 */

/**
 * 创建实现一个 文件后台下载功能，最好是批量下载文件，结合多线程，异步任务
 * */
public class ServiceExample {
}


/**
 * Service和Intent Service:没啥区别，只是IntentService在onCreate方法中开启新的HandlerThread去执行。

 Service运行的进程和线程：当它运行的时候如果是LocalService，那么对应的Service是运行在主进程的main线程上的。如onCreate,onStart这些函数都是在系统调用的时候在主进程的main线程上运行的。如果是RemoteSevice，那么对应的Service则是运行在独立的main线程上。

 服务不是单一的进程，服务没有自己的进程，应用程序可以不同，服务运行在相同的进程中
 服务不是线程，可以在线程中工作
 在应用中，如果是长时间的在后台运行，而且不需要交互的情况下，使用服务
 同样是在后台运行，不需要交互的情况下，如果只是完成某个任务，之后就不需要运行，而且可能是多个任务，需要长时间运行的情况下使用线程
 如果任务占用CPU时间多，资源大的情况下，要使用线程
 Thread的运行是独立于Activity的，也就是说当一个Activity被finish之后，如果你没有主动停止Thread或者Thread里的run方法没有执行完毕的话，Thread就会一直执行。


 * */