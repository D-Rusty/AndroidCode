package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck;


import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.flybehavior.GoodFlyBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior.GaGaQuackBehavior;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class GreenHeadDuck extends Duck {

    public GreenHeadDuck(){
        mFlyBehavior=new GoodFlyBehavior();
        mQuackBehavior=new GaGaQuackBehavior();
    }
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
}
