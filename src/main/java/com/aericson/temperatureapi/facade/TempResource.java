package com.aericson.temperatureapi.facade;

import com.aericson.temperatureapi.model.Measurement;
import com.aericson.temperatureapi.model.MeasurementsDTO;
import com.aericson.temperatureapi.service.TempService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensordata")
public class TempResource {

    private final TempService tempService;
    private final AuthHelper authHelper;

    public TempResource(TempService tempService, AuthHelper authHelper) {
        this.tempService = tempService;
        this.authHelper = authHelper;
    }

    @CrossOrigin() // TODO Add origin?
    @PostMapping
    public ResponseEntity<Boolean> addMeasurements(@RequestHeader(name = "uuidKey") String uuidKey, @RequestBody MeasurementsDTO measurementsDTO) throws Exception {
        return authHelper.isAuthorized(uuidKey) ? tempService.addMeasurement(measurementsDTO) : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @CrossOrigin() // TODO Add origin?
    @GetMapping
    public ResponseEntity<List<Measurement>> getMeasurements(
            @RequestHeader(name = "uuidKey") String uuidKey,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) throws Exception {
        return authHelper.isAuthorized(uuidKey) ? ResponseEntity.ok(tempService.getMeasurements(fromDate, toDate)) : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
