package com.aericson.temperatureapi.service;

import com.aericson.temperatureapi.integration.TempDBRepository;
import com.aericson.temperatureapi.model.Measurement;
import com.aericson.temperatureapi.model.MeasurementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TempService {

    @Autowired
    TempDBRepository tempDBRepository;

    public Boolean addMeasurement(MeasurementsDTO measurementsDTO) {
        Measurement measurement = new Measurement(measurementsDTO.getTemperature(), measurementsDTO.getHumidity(), measurementsDTO.getSensorId());
        Measurement result = tempDBRepository.insert(measurement);
        return true;
    }

    public List<Measurement> getAllMeasurements() {
        return tempDBRepository.findAll();
    }

    public List<Measurement> getMeasurementsBefore(String dateString) {
        return null;
    }

    public List<Measurement> getMeasurementsAfter(String dateString) {
        return null;
    }

    public List<Measurement> getMeasurementsBetween(String fromDateString, String toDateString) {
        return null;
    }
}
