package com.project.onepice.basicproject.androidBasic;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by onepice2015 on 16/5/30.
 */
public class AsyncTaskExample {
//new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, milliseconds,runnableTaskQueue, handler);
/**
 * corePoolSize 线程池的基本大小 当提交一个任务到线程池时，线程池会创建一个符合当前当前数量的线程池
 * maximumPoolSize 线程池允许创建的最大线程数，如果对列满了，并且已创建的线程数小于最大线程数，则线程池会在创建的线程执行任务
 * keepAliveTime 线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
 * milliseconds 线程活动保持时间的单位
 * runnableTaskQueue（任务队列）：用于保存等待执行的任务的阻塞队列。 可以选择以下几个阻塞队列。
 * ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
 * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按 FIFO （先进先出） 排序元素，吞吐量通常要高于 ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool() 使用了这个队列。
 * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法 Executors.newCachedThreadPool 使用了这个队列。
 * PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
 * ThreadFactory 用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
 * RejectedExecutionHandler 饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是 AbortPolicy，表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略。
 * AbortPolicy：直接抛出异常。
 * CallerRunsPolicy：只用调用者所在线程来运行任务。
 * DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
 * DiscardPolicy：不处理，丢弃掉。
 * 当然也可以根据应用场景需要来实现 RejectedExecutionHandler 接口自定义策略。如记录日志或持久化不能处理的任务。
 * */

/**
 corePoolSize 与 maximumPoolSize
 由于 ThreadPoolExecutor 将根据 corePoolSize 和 maximumPoolSize 设置的边界自动调整池大小，当新任务在方法 execute(java_green.lang.Runnable) 中提交时：

 如果运行的线程少于 corePoolSize，则创建新线程来处理请求，即使其他辅助线程是空闲的；
 如果设置的 corePoolSize 和 maximumPoolSize 相同，则创建的线程池是大小固定的，如果运行的线程数与 corePoolSize 相同，当有新请求过来时，若 workQueue 未满，则将请求放入 workQueue 中，等待有空闲的线程去从 workQueue 中取任务并处理
 如果运行的线程多于 corePoolSize 而少于 maximumPoolSize，则仅当队列满时才创建新线程去处理请求；
 如果运行的线程多于 corePoolSize 并且等于 maximumPoolSize，若队列已经满了，则通过RejectedExecutionHandler 所指定的策略来处理新请求；
 如果将 maximumPoolSize 设置为基本的无界值（如 Integer.MAX_VALUE），则允许池适应任意数量的并发任务
 也就是说，处理任务的优先级为：

 corePoolSize > workQueue > maximumPoolSize，如果三者都满了，使用 RejectedExecutionHandler 处理被拒绝的任务。
 当池中的线程数大于 corePoolSize 的时候，多余的线程会等待 keepAliveTime 长的时间，如果无请求可处理就自行销毁。




 workQueue线程池所使用的缓冲队列，该缓冲队列的长度决定了能够缓冲的最大数量，缓冲队列有三种通用策略：

 直接提交。工作队列的默认选项是 SynchronousQueue，它将任务直接提交给线程而不保持它们。在此，如果不存在可用于立即运行任务的线程，则试图把任务加入队列将失败，因此会构造一个新的线程。此策略可以避免在处理可能具有内部依赖性的请求集时出现锁。直接提交通常要求无界 maximumPoolSizes 以避免拒绝新提交的任务。当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性;
 无界队列。使用无界队列（例如，不具有预定义容量的 LinkedBlockingQueue）将导致在所有 corePoolSize 线程都忙时新任务在队列中等待。这样，创建的线程就不会超过 corePoolSize（因此，maximumPoolSize 的值也就无效了）。当每个任务完全独立于其他任务，即任务执行互不影响时，适合于使用无界队列；例如，在 Web 页服务器中。这种排队可用于处理瞬态突发请求，当命令以超过队列所能处理的平均数连续到达时，此策略允许无界线程具有增长的可能性;
 有界队列。当使用有限的 maximumPoolSizes 时，有界队列（如 ArrayBlockingQueue）有助于防止资源耗尽，但是可能较难调整和控制。队列大小和最大池大小可能需要相互折衷：使用大型队列和小型池可以最大限度地降低 CPU 使用率、操作系统资源和上下文切换开销，但是可能导致人工降低吞吐量。如果任务频繁阻塞（例如，如果它们是 I/O 边界），则系统可能为超过您许可的更多线程安排时间。使用小型队列通常要求较大的池大小，CPU 使用率较高，但是可能遇到不可接受的调度开销，这样也会降低吞吐量。

 ThreadFactory
 使用 ThreadFactory 创建新线程。如果没有另外说明，则在同一个 ThreadGroup 中一律使用 Executors.defaultThreadFactory() 创建线程，并且这些线程具有相同的 NORM_PRIORITY 优先级和非守护进程状态。通过提供不同的 ThreadFactory，可以改变线程的名称、线程组、优先级、守护进程状态等等。如果执行 newThread 时 ThreadFactory 未能创建线程（返回 null），则执行程序将继续运行，但不能执行任何任务。

 接下来我们看一下 ThreadPoolExecutor 中最重要的 execute 方法：



 public void execute(Runnable command) {
 if (command == null)
 throw new NullPointerException();
 //如果线程数小于基本线程数，则创建线程并执行当前任务
 if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
 //如线程数大于等于基本线程数或线程创建失败，则将当前任务放到工作队列中。
 if (runState == RUNNING && workQueue.offer(command)) {
 if (runState != RUNNING || poolSize == 0)
 ensureQueuedTaskHandled(command);
 }
 //如果线程池不处于运行中或任务无法放入队列，并且当前线程数量小于最大允许的线程数量，
 则创建一个线程执行任务。
 else if (!addIfUnderMaximumPoolSize(command))
 //抛出RejectedExecutionException异常
 reject(command); // is shutdown or saturated
 }
 }


 线程池容量的动态调整？

 ThreadPoolExecutor 提供了动态调整线程池容量大小的方法：setCorePoolSize() 和setMaximumPoolSize()：
 setCorePoolSize：设置核心池大小
 setMaximumPoolSize：设置线程池最大能创建的线程数目大小
 当上述参数从小变大时，ThreadPoolExecutor 进行线程赋值，还可能立即创建新的线程来执行任务。


 线程池的监控？

 通过线程池提供的参数进行监控。线程池里有一些属性在监控线程池的时候可以使用
 taskCount：线程池需要执行的任务数量。
 completedTaskCount：线程池在运行过程中已完成的任务数量。小于或等于 taskCount。
 largestPoolSize：线程池曾经创建过的最大线程数量。通过这个数据可以知道线程池是否满过。如等于线程池的最大大小，则表示线程池曾经满了。
 getPoolSize: 线程池的线程数量。如果线程池不销毁的话，池里的线程不会自动销毁，所以这个大小只增不减。
 getActiveCount：获取活动的线程数。
 通过扩展线程池进行监控。通过继承线程池并重写线程池的 beforeExecute，afterExecute 和 terminated 方法，我们可以在任务执行前，执行后和线程池关闭前干一些事情。如监控任务的平均执行时间，最大执行时间和最小执行时间等。
 * */

Executor executor=new ThreadPoolExecutor(5,10,10,TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(5));

}

/**
 * AyncTask的缺陷和问题
 * 关于线程池：asynctask对应的线程池ThreadPoolExecutor都是进程范围内共享的，都是static的，所以是asynctask控制着进程范围内所有的子类实例。
 * 由于这个限制的存在，当使用默认线程池时，如果线程数超过线程池的最大容量，线程池就会爆掉(3.0后默认串行执行，不会出现这个问题)。针对这种情况，可以尝试自定义线程池，配合asynctask使用。
 * 关于默认线程池：核心线程池中最多有CPU_COUNT+1个，最多有CPU_COUNT*2+1个，线程等待队列的最大等待数为128，但是可以自定义线程池。线程池是由AsyncTask来管理的，
 * 线程池允许tasks并行运行，xuyao注意的是并发情况下数据的一致性问题，新数据可能会被老数据覆盖掉，类似volatile变量。所以希望tasks能够串行运行的话，使用SERIAL_EXECUTOR。
 *
 * 死锁,资源不足,并发错误，线程泄漏，请求过载
 * */


/**
 *
 * 调用AsyncTask的excute方法不能立即执行程序的原因分析及改善方案
 * 通过查阅官方文档发现，AsyncTask首次引入时，异步任务是在一个独立的线程中顺序的执行，也就是说一次只能执行一个任务，不能并行的执行，从1.6开始，AsyncTask引入了线程池，
 * 支持同时执行5个异步任务，也就是说同时只能有5个线程运行，超过的线程只能等待，等待前面的线程某个执行完了才被调度和运行。换句话说，如果一个进程中的AsyncTask实例个数超过5个，那么假如前5个都运行很长时间的话，
 * 那么第6个只能等待机会了。这是AsyncTask的一个限制，而且对于2.3以前的版本无法解决。如果你的应用需要大量的后台线程去执行任务，那么你只能放弃使用AsyncTask，自己创建线程池来管理Thread，
 * 或者干脆不用线程池直接使用Thread也无妨。不得不说，虽然AsyncTask较Thread使用起来方便，但是它最多只能同时运行5个线程，这也大大局限了它的实力，你必须要小心设计你的应用，错开使用AsyncTask的时间，
 * 尽力做到分时，或者保证数量不会大于5个，否则就会遇到上次提到的问题。可能是Google意识到了AsyncTask的局限性了，从Android3.0开始对AsyncTask的API作出了一些调整：
 * 每次只启动一个线程执行一个任务，完成之后再执行第二个任务，也就是相当于只有一个后台线程在执行所提交的任务。
 * */


/**
 * 多文件下载demo，多文件上传demo，批量上传demo
 * */

























