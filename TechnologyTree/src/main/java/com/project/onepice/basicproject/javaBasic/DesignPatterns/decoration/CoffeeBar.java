package com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.coffee.Decaf;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.decorator.Chocolate;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class CoffeeBar {
    public static void main(String[]args){
        Drink order;
        order=new Decaf();
        System.out.println("order1 price:"+order.cost());
        System.out.println("order1 desc:"+order.getDescription());

        System.out.println("****************");
        order=new Decaf();
        order=new Chocolate(order);
        System.out.println("order2 price:"+order.cost());
        System.out.println("order2 desc:"+order.getDescription());
    }


    /**
     *
     * 装饰者模式：
     *  1. 着重于对类进行功能叠加，同一个类，进行叠加，所以总是需要用到super
     *  2. 装饰者模式: 动态的将新功能附加到对象上。在对象功能扩展方面，它 比继承更有弹性。
     * */
}
