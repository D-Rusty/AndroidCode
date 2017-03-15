package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.method.NYOrderPizza;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.method.OrderPizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class PizzaStroe {
    /**
     *
     * 工厂模式，解决类实例化问题
     * 工厂方式方法，具体功能放到子类实现
     * 静态工厂: 接口+具体工厂实现类
     * */

        public static void main(String[] args) {

            OrderPizza mOrderPizza;
            mOrderPizza=new NYOrderPizza();

        }



    }


