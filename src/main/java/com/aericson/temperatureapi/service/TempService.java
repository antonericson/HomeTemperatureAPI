package com.aericson.temperatureapi.service;

import com.aericson.temperatureapi.integration.TempDBRepository;
import com.aericson.temperatureapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TempService {

    private static final String DATE_REGEX = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d";

    @Autowired
    TempDBRepository tempDBRepository;

    public ResponseEntity<Boolean> addMeasurement(MeasurementRequest measurementRequest) {
        Measurement measurement = new Measurement(measurementRequest.getTemperature(), measurementRequest.getHumidity(), measurementRequest.getSensorId());
        Measurement result = tempDBRepository.insert(measurement);
        return result.getTime() != null ? ResponseEntity.ok(true) : new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<Measurement> getMeasurements(String fromDateString, String toDateString) {
        List<Measurement> measurementsList;
        if (isValidDateString(fromDateString) && isValidDateString(toDateString)) {
            measurementsList = getMeasurementsBetween(fromDateString, toDateString);
        }
        else if (isValidDateString(fromDateString)) {
            measurementsList = getMeasurementsAfter(fromDateString);
        }
        else if (isValidDateString(toDateString)) {
            measurementsList = getMeasurementsBefore(toDateString);
        }
        else {
            measurementsList = tempDBRepository.findAll();
        }

        return measurementsList;
    }

    public Measurement getCurrentMeasurement() {
        return tempDBRepository.findFirstByOrderByTimeDesc();
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

    private boolean isValidDateString(String dateToCheck) {
        return dateToCheck != null && dateToCheck.matches(DATE_REGEX);
    }
}
