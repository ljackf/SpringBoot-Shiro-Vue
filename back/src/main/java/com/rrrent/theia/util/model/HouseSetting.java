package com.rrrent.theia.util.model;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:房屋配置
 */
public enum HouseSetting {

    TV("电视"),ICEBOX("冰箱"),WASHING("洗衣机"),AIRCONDITIONER("空调"),HEATER("热水器"),BED("床"),STEAM("暖气"),
    BROADBAND("宽带"),WARDROBE("衣柜"),GASSTOVE("燃气灶"),SOFA("沙发"),TABLESCHAIRS("桌椅"),LAMPBLACKMACHINE("油烟机"),
    ELECTROMAGNETICFURNACE("电磁炉"),MICROWAVEOVEN("微波炉");

    private String name;

    HouseSetting(String name) {
        this.name = name;
    }
}
