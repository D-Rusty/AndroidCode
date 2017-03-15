package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.pull;

/**
 * Created by onepice2015 on 16/6/20.
 */
public interface Observer {
    /**
     * 更新接口
     * @param subject 传入主题对象，方面获取相应的主题对象的状态
     */
    public void update(Subject subject);
}
