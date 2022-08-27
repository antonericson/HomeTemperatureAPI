package com.aericson.temperatureapi.model;

import lombok.Data;

@Data
public class MeasurementsDTO {
    private float temperature;
    private float humidity;
    private String sensorId;
}
