package com.hudong.wemedia.businesscomponent.coremodel.route.rxbus;

/**
 * 作者: 方天文
 * 日期: 2017/5/6:上午9:55
 * 概况:
 */

public class SubscriberEvent<T> {
    private Object data;
    private String tag;
    private boolean isSticky;

    public SubscriberEvent(String tag, Object o) {
        this.data = o;
        this.tag = tag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSticky() {
        return isSticky;
    }

    public void setSticky(boolean sticky) {
        isSticky = sticky;
    }

    public SubscriberEvent(String tag, Object data, boolean isSticky) {
        this.data = data;
        this.tag = tag;
        this.isSticky = isSticky;

    }
}
