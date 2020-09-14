package com.git.renan.repositoryfront.model;

public enum MenuType {

    NORMAL(1001), HEADER(1002), SUB_HEADER(1003), DIVIDER(1004);

    private final int value;

    MenuType(int value){ this.value = value; }
    public int getValue(){ return value; }
}
