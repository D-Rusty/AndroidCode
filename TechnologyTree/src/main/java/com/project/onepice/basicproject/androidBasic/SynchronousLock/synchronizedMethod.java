package com.project.onepice.basicproject.androidBasic.SynchronousLock;

/**
 * Created by onepice2015 on 16/6/30.
 *
 * 同步方法
 *
 *
 */
public class synchronizedMethod {


    //====================================同步方法================================
    private int count=0;//账户余额

    //存钱
    public synchronized void addMoney(int money){
        count +=money;
        System.out.println(System.currentTimeMillis()+"存进： "+money);
    }

    //取钱
    public synchronized void subMoney(int money){
        if (count-money<0){
            System.out.println("余额不足");
            return;
        }

        count -= money;
        System.out.println(System.currentTimeMillis()+"取出："+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }

    //====================================同步代码块================================



}


/**
 * 1.synchronized关键字也可以修饰静态方法，此时如果调用该静态方法，将会锁住整个类
 *
 *
 * */