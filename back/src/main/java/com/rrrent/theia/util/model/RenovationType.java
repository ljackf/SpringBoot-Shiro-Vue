package com.rrrent.theia.util.model;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:装修类型
 */
public enum RenovationType {
    HAUTECOUTURE("豪装"),HARDCOVER("精装"),MEDIUMDECORATION("中装"),PAPERBACK("简装"),ROUGHCAST("毛坯");

    private String name;

    RenovationType(String name) {
        this.name = name;
    }
}
