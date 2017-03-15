package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.observer;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
