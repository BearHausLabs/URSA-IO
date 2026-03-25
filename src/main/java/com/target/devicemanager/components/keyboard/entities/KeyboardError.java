package com.target.devicemanager.components.keyboard.entities;

import com.target.devicemanager.common.entities.DeviceError;
import org.springframework.http.HttpStatus;

public class KeyboardError extends DeviceError {
    public static final DeviceError EVENT_READ_FAILED = new KeyboardError("EVENT_READ_FAILED", "Failed to read keyboard event data", HttpStatus.PRECONDITION_FAILED);

    public KeyboardError(String code, String description, HttpStatus statusCode) {
        super(code, description, statusCode);
    }
}
