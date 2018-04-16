package com.d3dgrin.weatherparser.mvp.contract;

public interface BasePresenter<V> {
    void onAttachView(V attachedView);
    void onDetachView();
}
