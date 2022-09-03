package com.aericson.temperatureapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MeasurementData {
    private LocalDateTime time;
    private float measurement;
}
