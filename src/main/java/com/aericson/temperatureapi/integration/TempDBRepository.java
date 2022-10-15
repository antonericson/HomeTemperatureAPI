package com.aericson.temperatureapi.integration;

import com.aericson.temperatureapi.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TempDBRepository extends MongoRepository<Measurement, String> {

    List<Measurement> findByTimeGreaterThan(LocalDateTime fromDate);

    List<Measurement> findByTimeLessThan(LocalDateTime toDate);

    List<Measurement> findByTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

    Measurement findFirstByOrderByTimeDesc();
}
