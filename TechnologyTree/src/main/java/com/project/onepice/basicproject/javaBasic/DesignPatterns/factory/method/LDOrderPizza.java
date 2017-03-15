package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.method;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.LDCheesePizza;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.LDPepperPizza;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.Pizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class LDOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;

    }

}

