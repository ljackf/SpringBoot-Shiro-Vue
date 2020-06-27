package com.rrrent.theia.util.model;

/**
 * @author : jack
 * @date : 2020/6/27
 * @Description:
 */
public enum ArchitectureAge {

    BEFORE90("90年代之前"),F90T00("90年代"),F00T10("00年代"),F10T20("10年代"),AFTER20("20年代之后");

    private String name;

    ArchitectureAge(String name) {
        this.name = name;
    }
}
