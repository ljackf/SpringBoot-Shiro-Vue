package com.rrrent.theia.util.model;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:户型
 */
public enum HouseLayout {

    COTENANCY("合租"),WHOLERENT("整租"),SHOPOFFICE("商铺办公");

    private String name;

    HouseLayout(String name) {
        this.name = name;
    }

}
