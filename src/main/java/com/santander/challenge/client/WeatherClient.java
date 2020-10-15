package com.santander.challenge.client;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.santander.challenge.cache.WeatherCache;
import com.santander.challenge.model.MeetUp;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author guillermovarelli
 */
@Component
@ConfigurationProperties(prefix="weather")
public class WeatherClient {

    private ObjectMapper mapper;

    private String url;
    private String key;
    private String host;
    private String unit; // para setear grados centigrados
    private Integer days;


    public WeatherClient() {
        this.mapper = new ObjectMapper();
    }

    public Response getWeather(String city) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getUrl().concat("?q=").concat(city).concat("&cnt=").concat(getDays().toString()).concat("&units=").concat(getUnit()))
                .get()
                .addHeader("x-rapidapi-host", getHost())
                .addHeader("x-rapidapi-key", getKey())
                .build();

        Response response = client.newCall(request).execute();
        return  response;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
