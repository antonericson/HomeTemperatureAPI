package com.aericson.temperatureapi.service;

import com.aericson.temperatureapi.integration.TempDBRepository;
import com.aericson.temperatureapi.model.Measurement;
import com.aericson.temperatureapi.model.MeasurementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TempService {

    @Autowired
    TempDBRepository tempDBRepository;

    public ResponseEntity<Boolean> addMeasurement(MeasurementsDTO measurementsDTO) {
        Measurement measurement = new Measurement(measurementsDTO.getTemperature(), measurementsDTO.getHumidity(), measurementsDTO.getSensorId());
        Measurement result = tempDBRepository.insert(measurement);
        return result.getTime() != null ? ResponseEntity.ok(true) : new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<Measurement> getMeasurements(String fromDateString, String toDateString) {
        if (fromDateString == null && toDateString == null) {
            return tempDBRepository.findAll();
        }

        if (fromDateString != null && toDateString != null) {
            return getMeasurementsBetween(fromDateString, toDateString);
        }

        if (fromDateString != null) {
            return getMeasurementsAfter(fromDateString);
        }

        return getMeasurementsBefore(toDateString);
    }

    private List<Measurement> getMeasurementsBefore(String toDateString) {
        LocalDateTime toDate = LocalDateTime.parse(toDateString);
        return tempDBRepository.findByTimeLessThan(toDate);
    }

    private List<Measurement> getMeasurementsAfter(String fromDateString) {
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString);
        return tempDBRepository.findByTimeGreaterThan(fromDate);
    }

    private List<Measurement> getMeasurementsBetween(String fromDateString, String toDateString) {
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString);
        LocalDateTime toDate = LocalDateTime.parse(toDateString);
        return tempDBRepository.findByTimeBetween(fromDate, toDate);
    }
}
