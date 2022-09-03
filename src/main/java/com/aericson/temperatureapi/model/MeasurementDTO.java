package com.aericson.temperatureapi.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeasurementDTO {
    private String sensorId;
    private TemperatureData temperatureData;
    private HumidityData humidityData;

    public MeasurementDTO(Measurement measurement) {
        setSensorId(measurement.getSensorId());
        setTemperatureData(new TemperatureData(measurement.getTime(), measurement.getTemperature()));
        setHumidityData(new HumidityData(measurement.getTime(), measurement.getHumidity()));
    }
}

class TemperatureData extends MeasurementData {
    public TemperatureData(LocalDateTime time, float measurement) {
        super(time, measurement);
    }
}
class HumidityData extends MeasurementData {
    public HumidityData(LocalDateTime time, float measurement) {
        super(time, measurement);
    }
}