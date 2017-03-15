package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.push;

/**
 * Created by onepice2015 on 16/6/20.
 */
public interface Observer {
    /**
     * 更新接口
     * @param state    更新的状态
     */
    public void update(String state);
}
