package com.santander.challenge.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.santander.challenge.model.response.WeatherResponse;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author guillermovarelli
 */
@Component
public class WeatherMapper {
    public WeatherResponse mapToWeatherResponse(int day,Response response) throws IOException {

        JsonObject jobj = new Gson().fromJson(response.body().string(), JsonObject.class);
        WeatherResponse result = new WeatherResponse();


        result.setTemperature_min(jobj.getAsJsonArray("list").get(day).getAsJsonObject().getAsJsonObject("temp").get("min").getAsDouble());
         result.setTemperature_max(jobj.getAsJsonArray("list").get(day).getAsJsonObject().getAsJsonObject("temp").get("max").getAsDouble());


        return result;
    }
}
