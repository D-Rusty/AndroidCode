package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        super.setname("LDPepperPizza");

        System.out.println(name+" preparing;");
    }

}
