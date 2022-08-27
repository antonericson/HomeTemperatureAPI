package com.aericson.temperatureapi.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthHelper {

    @Value("${auth.uuid.key}")
    private String authKey;

    public boolean isAuthorized(final String key) {
        return authKey.equals(key);
    }
}
