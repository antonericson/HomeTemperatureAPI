package com.aericson.temperatureapi.integration;

import com.aericson.temperatureapi.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Date;
import java.util.List;

public interface TempDBRepository extends MongoRepository<Measurement, String> {

    List<Measurement> findByTimeGreaterThan(Date time);

    List<Measurement> findByTimeLessThan(Date time);

    List<Measurement> findByTimeBetween(Date from, Date to);
}
