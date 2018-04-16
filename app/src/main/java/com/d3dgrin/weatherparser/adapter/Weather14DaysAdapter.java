package com.d3dgrin.weatherparser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d3dgrin.weatherparser.R;
import com.d3dgrin.weatherparser.entity.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Weather14DaysAdapter extends RecyclerView.Adapter<Weather14DaysAdapter.ViewHolder> {

    private List<Weather> weatherList = new ArrayList<>();

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_14days_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.bind(weather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDayDate)
        TextView tvDayDate;

        @BindView(R.id.ivDayImage)
        ImageView ivDayImage;

        @BindView(R.id.tvDayDetail)
        TextView tvDayDetail;

        @BindView(R.id.tvDayTemp)
        TextView tvDayTemp;

        @BindView(R.id.ivNightImage)
        ImageView ivNightImage;

        @BindView(R.id.tvNightDetail)
        TextView tvNightDetail;

        @BindView(R.id.tvNightTemp)
        TextView tvNightTemp;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Weather weather) {
            Picasso.with(ivDayImage.getContext()).load(weather.getDayImage()).into(ivDayImage);
            Picasso.with(ivNightImage.getContext()).load(weather.getNightImage()).into(ivNightImage);

            String dayAndDate = weather.getDay() + ", " + weather.getDate();
            tvDayDate.setText(dayAndDate);

            tvDayDetail.setText(weather.getDayText());
            String dayTemp = weather.getDayTemp() + "°";
            tvDayTemp.setText(dayTemp);

            tvNightDetail.setText(weather.getNightText());
            String nightTemp = weather.getNightTemp() + "°";
            tvNightTemp.setText(nightTemp);
        }
    }
}
