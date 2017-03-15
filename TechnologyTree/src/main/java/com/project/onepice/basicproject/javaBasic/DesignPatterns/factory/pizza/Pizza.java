package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public abstract class Pizza {
    protected String name;

    public abstract void prepare();
    public void bake()
    {
        System.out.println(name+" baking;");
    }
    public void cut()
    {
        System.out.println(name+" cutting;");
    }
    public void box()
    {
        System.out.println(name+" boxing;");
    }
    public void setname(String name)
    {
        this.name=name;
    }
}
