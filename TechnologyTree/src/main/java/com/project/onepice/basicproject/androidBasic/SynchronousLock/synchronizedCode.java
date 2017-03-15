package com.project.onepice.basicproject.androidBasic.SynchronousLock;

/**
 * Created by onepice2015 on 16/6/30.
 *
 * 同步代码块
 */
public class synchronizedCode {

    private int count =0;//账户余额

    //存钱
    public void addMoney(int money){

        synchronized (this){
            count+=money;
        }

        System.out.println(System.currentTimeMillis()+"存进: "+money);
    }

    //取钱
    public void  subMoney(int money){
        synchronized (this){
            if (count-money<0){
                System.out.println("余额不足");
                return;
            }
            count-=money;
        }

        System.out.println(System.currentTimeMillis()+"取出: "+money);

    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额: "+count);
    }

}
/**
 *同步是一种高开销的操作，因此应该尽量减少同步的内容，通常没有必要同步整个方法，同步关键代码即可
 *
 * */