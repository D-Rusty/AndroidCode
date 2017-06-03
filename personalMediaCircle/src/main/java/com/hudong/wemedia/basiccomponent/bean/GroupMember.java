package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/4/23:下午4:53
 * 概况:
 */

public class GroupMember {
    private String id;
    private String mid;
    private String intropenid;
    private String headimg;
    private String status;
    private String manage;
    private String realname;
    private String groupId;
    private String isSelected;

    public GroupMember() {
    }

    public GroupMember(String id, String mid, String intropenid, String headimg, String status, String manage, String realname, String groupId, String isSelected) {
        this.id = id;
        this.mid = mid;
        this.intropenid = intropenid;
        this.headimg = headimg;
        this.status = status;
        this.manage = manage;
        this.realname = realname;
        this.groupId = groupId;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIntropenid() {
        return intropenid;
    }

    public void setIntropenid(String intropenid) {
        this.intropenid = intropenid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "id='" + id + '\'' +
                ", mid='" + mid + '\'' +
                ", intropenid='" + intropenid + '\'' +
                ", headimg='" + headimg + '\'' +
                ", status='" + status + '\'' +
                ", manage='" + manage + '\'' +
                ", realname='" + realname + '\'' +
                ", groupId='" + groupId + '\'' +
                ", isSelected='" + isSelected + '\'' +
                '}';
    }
}
