package com.pais.home.dagger;

import com.pais.home.view.HomeActivity;

import dagger.Component;

/**
 * Created by SSL-D on 2016-07-20.
 */
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
