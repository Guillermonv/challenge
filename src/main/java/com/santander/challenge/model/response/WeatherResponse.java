package com.santander.challenge.model.response;

/**
 * @author guillermovarelli
 */
public class WeatherResponse {

  private double temperature_min;
  private double temperature_max;

    public double getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(double temperature_min) {
        this.temperature_min = temperature_min;
    }

    public double getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(double temperature_max) {
        this.temperature_max = temperature_max;
    }
}
