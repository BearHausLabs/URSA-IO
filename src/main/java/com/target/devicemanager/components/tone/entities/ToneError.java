package com.target.devicemanager.components.tone.entities;

import com.target.devicemanager.common.entities.DeviceError;
import org.springframework.http.HttpStatus;

public class ToneError extends DeviceError {
    public static final DeviceError TONE_FAILED = new ToneError("TONE_FAILED", "Failed to play tone", HttpStatus.PRECONDITION_FAILED);

    public ToneError(String code, String description, HttpStatus statusCode) {
        super(code, description, statusCode);
    }
}
