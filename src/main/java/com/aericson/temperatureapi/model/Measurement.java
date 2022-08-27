package com.aericson.temperatureapi.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("testTempCollection") //TODO Update to "tempdata" when working
@Data
public class Measurement {

    private LocalDateTime time;
    private int temperature;
    private int humidity;
    private String sensorId;

    public Measurement(int temperature, int humidity, String sensorId) {
        super();
        this.temperature = temperature;
        this.humidity = humidity;
        this.sensorId = sensorId;
        this.time = LocalDateTime.now();
    }
}
