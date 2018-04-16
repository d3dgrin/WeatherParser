package com.d3dgrin.weatherparser;

import android.app.Application;
import android.content.Context;

import com.d3dgrin.weatherparser.dagger.component.AppComponent;
import com.d3dgrin.weatherparser.dagger.component.DaggerAppComponent;
import com.d3dgrin.weatherparser.dagger.module.AppModule;

public class App extends Application {
    private AppComponent appComponent;

    public static App get(Context context) {
        return (App)context.getApplicationContext();
    }

    public AppComponent getInjector() {
        if(appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
