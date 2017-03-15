package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class GaGaQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("__GaGa__");
    }
}
