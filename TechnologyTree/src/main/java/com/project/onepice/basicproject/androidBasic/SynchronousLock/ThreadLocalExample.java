package com.project.onepice.basicproject.androidBasic.SynchronousLock;

/**
 * Created by onepice2015 on 16/6/30.
 *
 * 使用局部变量实现线程同步
 */
public class ThreadLocalExample {
    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //存钱
    public void addMoney(int money){
        count.set(count.get()+money);
        System.out.println(System.currentTimeMillis()+"李进: "+money);
    }

    //取钱
    public void  subMoney(int money){
        if (count.get() -money<0){
            System.out.println("余额不足");
            return;
        }

        count.set(count.get()-money);
        System.out.println(System.currentTimeMillis()+"取出： "+money);
    }

    //查询

    public void lookMoney(){
        System.out.println("账户余额: "+count.get());
    }
}

/*
*
* 原来每个线程运行的都是一个副本，也就是说存钱和取钱是两个账户，知识名字相同而已。所以就会发生上面的效果。

ThreadLocal与同步机制

a.ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题
b.前者采用以”空间换时间”的方法，后者采用以”时间换空间”的方式
* **/