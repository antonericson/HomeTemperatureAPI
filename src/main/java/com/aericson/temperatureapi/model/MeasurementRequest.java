package com.aericson.temperatureapi.model;

import lombok.Data;

@Data
public class MeasurementRequest {
    private float temperature;
    private float humidity;
    private String sensorId;
}
