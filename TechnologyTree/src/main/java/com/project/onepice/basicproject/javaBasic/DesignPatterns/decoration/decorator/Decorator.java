package com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.decorator;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.Drink;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public  class Decorator extends Drink {
    private Drink Obj;

    public Decorator(Drink Obj){
        this.Obj=Obj;
    };


    @Override
    public float cost() {
        // TODO Auto-generated method stub

        return super.getPrice()+Obj.cost();
    }

    @Override
    public String getDescription()
    {
        return super.description+"-"+super.getPrice()+"&&"+Obj.getDescription();
    }

}
