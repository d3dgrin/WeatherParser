package com.d3dgrin.weatherparser.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.d3dgrin.weatherparser.App;
import com.d3dgrin.weatherparser.R;
import com.d3dgrin.weatherparser.adapter.Weather14DaysAdapter;
import com.d3dgrin.weatherparser.entity.Weather;
import com.d3dgrin.weatherparser.mvp.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mainPresenter;

    @BindView(R.id.rvWeather)
    RecyclerView rvWeather;

    @BindView(R.id.btnRefresh)
    Button btnRefresh;

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    private Weather14DaysAdapter weather14DaysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        App.get(this).getInjector().inject(this);

        initRV();

        btnRefresh.setOnClickListener(v -> mainPresenter.loadWeather());

        mainPresenter.onAttachView(this);
        mainPresenter.loadWeather();
    }

    private void initRV() {
        weather14DaysAdapter = new Weather14DaysAdapter();
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        rvWeather.setHasFixedSize(true);
        rvWeather.setAdapter(weather14DaysAdapter);
    }

    @Override
    public void setWeather(List<Weather> weatherList) {
        weather14DaysAdapter.setWeatherList(weatherList);
    }

    @Override
    public void showLoading() {
        rvWeather.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rvWeather.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetachView();
    }
}
