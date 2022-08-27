package com.aericson.temperatureapi.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("tempdata")
@Data
public class Measurement {

    private LocalDateTime time;
    private float temperature;
    private float humidity;
    private String sensorId;

    public Measurement(float temperature, float humidity, String sensorId) {
        super();
        this.temperature = temperature;
        this.humidity = humidity;
        this.sensorId = sensorId;
        this.time = LocalDateTime.now();
    }
}
