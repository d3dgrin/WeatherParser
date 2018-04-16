package com.d3dgrin.weatherparser.dagger.component;

import com.d3dgrin.weatherparser.dagger.module.AppModule;
import com.d3dgrin.weatherparser.mvp.presenter.MainPresenter;
import com.d3dgrin.weatherparser.mvp.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
    void inject(MainActivity mainActivity);
}
