package com.d3dgrin.weatherparser.mvp.model;

import com.d3dgrin.weatherparser.entity.Weather;
import com.d3dgrin.weatherparser.mvp.contract.MainContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainModel implements MainContract.Model {
    @Override
    public Observable<List<Weather>> loadWeather() {
        List<Weather> weatherList = new ArrayList<>();
        return Observable.fromCallable(() -> {
            Document doc = Jsoup.connect("https://www.gismeteo.ua/weather-dnipro-5077/14-days/").get();

            Elements blocks = doc.select(".rframe.wblock.wdata");

            for(Element element: blocks) {
                Element eShort = element.selectFirst(".wbshort table tbody tr");

                Element eDay = eShort.selectFirst(".workday");
                if (eDay == null) eDay = eShort.selectFirst(".weekend");

                String day = eDay.selectFirst(".weekday").text();
                String date = eDay.selectFirst(".s_date").text();

                String dayText = eShort.select(".cltext").get(1).text();
                String dayTemp = eShort.select(".temp").get(1).selectFirst("span").text();
                String dayImage = eShort.select(".clicon").get(1)
                        .selectFirst("img").attr("src");
                dayImage = "https:" + dayImage;

                String nightText = eShort.select(".cltext").get(0).text();
                String nightTemp = eShort.select(".temp").get(0).selectFirst("span").text();
                String nightImage = eShort.select(".clicon").get(0)
                        .selectFirst("img").attr("src");
                nightImage = "https:" + nightImage;

                weatherList.add(new Weather(
                        day,
                        date,
                        dayTemp,
                        dayText,
                        dayImage,
                        nightTemp,
                        nightText,
                        nightImage
                ));
            }
            return weatherList;
        });
    }
}
