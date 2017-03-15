package com.project.onepice.basicproject.bean;

/**
 * Created by onepice2015 on 2017/1/25.
 */

public class Section {
    private final String name;

    public boolean isExpanded;

    public Section(String name) {
        this.name = name;
        isExpanded = true;
    }

    public String getName() {
        return name;
    }
}
