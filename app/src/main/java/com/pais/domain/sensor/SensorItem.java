package com.pais.domain.sensor;

/**
 * Created by SSL-D on 2016-07-21.
 */

public class SensorItem {
    private int id;
    private String name;
    private String serial;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSerial() {
        return serial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
