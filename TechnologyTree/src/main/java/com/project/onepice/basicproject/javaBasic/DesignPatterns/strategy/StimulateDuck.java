package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy;


import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck.Duck;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck.GreenHeadDuck;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck.RedHeadDuck;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.flybehavior.NoFlyBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior.NoQuackBehavior;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class StimulateDuck {
    public static void main(String[]args){
        Duck mGreenHeadDuck = new GreenHeadDuck();
        Duck mRedHeadDuck = new RedHeadDuck();

        mGreenHeadDuck.display();
        mGreenHeadDuck.Fly();
        mGreenHeadDuck.Quack();
        mGreenHeadDuck.swim();

        mRedHeadDuck.display();
        mRedHeadDuck.Fly();
        mRedHeadDuck.Quack();
        mRedHeadDuck.swim();
        mRedHeadDuck.display();
        mRedHeadDuck.SetFlyBehavoir(new NoFlyBehavior());
        mRedHeadDuck.Fly();
        mRedHeadDuck.SetQuackBehavoir(new NoQuackBehavior());
        mRedHeadDuck.Quack();
    }
}
