package com.pais.sensormonitor.dagger;

import com.pais.sensormonitor.view.SensorMonitorFragment;

import dagger.Component;

/**
 * Created by SSL-D on 2016-08-01.
 */
@Component(modules = SensorMonitorModule.class)
public interface SensorMonitorComponent {
    void inject(SensorMonitorFragment sensorMonitorFragment);
}
