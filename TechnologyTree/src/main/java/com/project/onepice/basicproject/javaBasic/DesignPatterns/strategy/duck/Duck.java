package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.flybehavior.FlyBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.flybehavior.NoFlyBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior.NoQuackBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior.QuackBehavior;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public abstract class Duck {


    FlyBehavior mFlyBehavior;
    QuackBehavior mQuackBehavior;

    public Duck() {

    }

    public void Fly() {
        mFlyBehavior.fly();
    }

    public void Quack() {
        mQuackBehavior.quack();
    }

    public abstract void display();

    public void SetQuackBehavoir(NoQuackBehavior qb) {
        mQuackBehavior = qb;
    }

    public void SetFlyBehavoir(NoFlyBehavior fb) {
        mFlyBehavior = fb;
    }

    public void swim() {
        System.out.println("~~im swim~~");
    }
}

