package com.d3dgrin.weatherparser.mvp.presenter;

import android.content.Context;

import com.d3dgrin.weatherparser.App;
import com.d3dgrin.weatherparser.mvp.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    @Inject
    MainContract.Model model;

    private MainContract.View view;

    public MainPresenter(Context context) {
        App.get(context).getInjector().inject(this);
    }

    @Override
    public void onAttachView(MainContract.View attachedView) {
        view = attachedView;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void loadWeather() {
        view.showLoading();
        model.loadWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weathers -> {
                    view.hideLoading();
                    view.setWeather(weathers);
                }, throwable -> {
                    view.hideLoading();
                    view.showError("No internet connection. Please, try again.");
                });
    }
}
