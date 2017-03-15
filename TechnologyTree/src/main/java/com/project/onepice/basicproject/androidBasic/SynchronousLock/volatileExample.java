package com.project.onepice.basicproject.androidBasic.SynchronousLock;

/**
 * Created by onepice2015 on 16/6/30.
 *
 * 1. volatile 关键字为域变量的访问提供了一种免锁机制
 * 2. 使用volatile修饰域相当于告诉虚拟机该域可能会被其它线程更新
 *    因此每次使用该域就要重新计算，而不是使用寄存器中的值
 * 3. volatile不会提供任何原子操作，它也不能用来修饰final类型的变量
 *
 */
public class volatileExample {

    private  volatile  int count = 0;//账号余额

    //存钱
    public void addMoney(int money){
        count +=money;
        System.out.println(System.currentTimeMillis()+"存进: "+money);
    }

    //取钱
    public void subMoney(int money){
        if (count-money<0){
            System.out.println("余额不足");
            return;
        }

        count -=money;
        System.out.println(System.currentTimeMillis()+ "取出: "+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额: "+count);
    }
}

/**
 * volatile不能保证原子操作导致的，因此volatile不能代替synchronized。此外volatile会组织编译器对代码优化，因此能不使用它就不适用它吧。它的原理是每次要线程要访问volatile修饰的变量时都是从内存中读取，而不是存缓存当中读取，因此每个线程访问到的变量值都是一样的。这样就保证了同步。
 *
 * */
