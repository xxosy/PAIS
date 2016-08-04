package com.pais.house.dagger;

import com.pais.house.view.HouseFragment;

import dagger.Component;

/**
 * Created by SSL-D on 2016-08-03.
 */
@Component(modules = HouseModule.class)
public interface HouseComponent {
    void Inject(HouseFragment houseFragment);
}
