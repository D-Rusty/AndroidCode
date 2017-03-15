package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * 摩卡装饰者
 *
 * Created by onepice2015 on 16/6/20.
 */
public class soy extends  CondimentDecorator{

    Beverage beverage;

    public soy(Beverage beverage){
        this.beverage=beverage;
    }


    @Override
    public String getDescription() {
        return beverage.getDescription()+",soy";
    }

    @Override
    public double cost() {
        return 0.4 + beverage.cost();
    }
}
