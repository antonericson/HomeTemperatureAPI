package com.aericson.temperatureapi.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Date;

@Document("testTempCollection") //TODO Update to "tempdata" when working
@Data
public class Measurement {

    private Date time;
    private int temperature;
    private int humidity;
    private String sensorId;

    public Measurement(int temperature, int humidity, String sensorId) {
        super();
        this.temperature = temperature;
        this.humidity = humidity;
        this.sensorId = sensorId;
        this.time = Date.from(Instant.parse("2023-08-27T15:55:22.672+00:00"));
    }
}
