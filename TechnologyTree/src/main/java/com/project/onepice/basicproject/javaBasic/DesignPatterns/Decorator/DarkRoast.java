package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * 混合咖啡
 *
 * 被装饰者1
 *
 * Created by onepice2015 on 16/6/20.
 */
public class DarkRoast extends Beverage{

    public DarkRoast(){
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 0.90;
    }
}
