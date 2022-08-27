package com.aericson.temperatureapi.model;

import lombok.Data;

@Data
public class MeasurementsDTO {
    private int temperature;
    private int humidity;
    private String sensorId;
}
