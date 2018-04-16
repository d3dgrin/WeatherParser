package com.d3dgrin.weatherparser.mvp.contract;

import com.d3dgrin.weatherparser.entity.Weather;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {
    interface View {
        void setWeather(List<Weather> weatherList);
        void showLoading();
        void hideLoading();
        void showError(String msg);
    }
    interface Presenter extends BasePresenter<View> {
        void loadWeather();
    }
    interface Model {
        Observable<List<Weather>> loadWeather();
    }
}
