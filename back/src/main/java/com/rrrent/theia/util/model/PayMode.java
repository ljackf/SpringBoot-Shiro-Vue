package com.rrrent.theia.util.model;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:装修类型
 */
public enum PayMode {
    ONEFORTHREE("押一付三"),ONEFORONE("押一付一"),TWOFORTHREE("押二付三"),HARFYEAR("半年付"),ONEYEAR("年付"),OTHER("其他");

    private String name;

    PayMode(String name) {
        this.name = name;
    }
}
