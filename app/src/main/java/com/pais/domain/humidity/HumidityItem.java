package com.pais.domain.humidity;

/**
 * Created by SSL-D on 2016-07-21.
 */

public class HumidityItem {
    private int id;
    private String value;
    private int sensor_id;

    public int getId() {
        return id;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
