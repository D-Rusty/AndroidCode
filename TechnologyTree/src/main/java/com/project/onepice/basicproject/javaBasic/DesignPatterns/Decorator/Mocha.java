package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * 摩卡装饰者
 *
 * Created by onepice2015 on 16/6/20.
 */
public class Mocha extends  CondimentDecorator{

    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage=beverage;
    }


    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
