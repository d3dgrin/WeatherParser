package com.d3dgrin.weatherparser.dagger.module;

import android.content.Context;

import com.d3dgrin.weatherparser.mvp.contract.MainContract;
import com.d3dgrin.weatherparser.mvp.model.MainModel;
import com.d3dgrin.weatherparser.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    MainContract.Model provideMainModel() {
        return new MainModel();
    }

    @Singleton
    @Provides
    MainContract.Presenter provideMainPresenter(Context context) {
        return new MainPresenter(context);
    }
}
