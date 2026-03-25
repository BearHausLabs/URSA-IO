package com.target.devicemanager.components.msr.entities;

import com.target.devicemanager.common.entities.DeviceError;
import org.springframework.http.HttpStatus;

public class MsrError extends DeviceError {
    public static final DeviceError READ_FAILED = new MsrError("READ_FAILED", "Failed to read card", HttpStatus.PRECONDITION_FAILED);
    public static final DeviceError NO_DATA = new MsrError("NO_DATA", "No card data received", HttpStatus.PRECONDITION_FAILED);

    public MsrError(String code, String description, HttpStatus statusCode) {
        super(code, description, statusCode);
    }
}
