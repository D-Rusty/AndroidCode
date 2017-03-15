package com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public abstract class Drink {
    public String description="";
    private float price=0f;;


    public void setDescription(String description)
    {
        this.description=description;
    }

    public String getDescription()
    {
        return description+"-"+this.getPrice();
    }
    public float getPrice()
    {
        return price;
    }
    public void setPrice(float price)
    {
        this.price=price;
    }
    public abstract float cost();

}

