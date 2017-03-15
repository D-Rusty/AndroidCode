package com.project.onepice.basicproject.androidBasic.SynchronousLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by onepice2015 on 16/6/30.
 *
 * 重入锁 example
 */
public class concurrentExample {

    private int count =0;//账户余额

    //需要声明这个锁
    private Lock lock = new ReentrantLock();

    //存钱
    public void addMoney(int money){
        lock.lock();//上锁
        try{
            count+=money;
            System.out.println(System.currentTimeMillis()+"存进："+money);
        }finally {
            lock.unlock();//解锁
        }
    }

    //取钱
    public void subMoney(int money){
        lock.lock();

        try{
            if (count-money <0){
                System.out.println("余额不足");
                return;
            }
            count-=money;
            System.out.println(System.currentTimeMillis()+"取出: "+money);
        }finally {
            lock.unlock();
        }
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额: "+count);
    }
}
