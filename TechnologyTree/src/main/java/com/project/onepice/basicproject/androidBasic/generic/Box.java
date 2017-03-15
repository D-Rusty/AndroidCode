package com.project.onepice.basicproject.androidBasic.generic;

/**
 * Created by onepice2015 on 16/6/23.
 */
public class Box<T> {

     private T t;

     public void set(T t){
       this.t=t;
     }

    public T get(){
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: "+t.getClass().getName());
        System.out.println("U: "+u.getClass().getName());
    }

    public static void main(String[] args){
        Box<String> integerBox=new Box<>();
        integerBox.set("abc");//能通过编译，因为T指定为String类型
//        integerBox.inspect("abc");//不能通过编译，因为U必须是Number类型或其子类
        integerBox.inspect(new Integer(10));
    }

}
