package com.target.devicemanager.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Response DTO for device lifecycle status queries.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeviceLifecycleResponse {
    private final DeviceLifecycleState state;
    private final String logicalName;
    private final boolean manualMode;
    private final String deviceType;

    public DeviceLifecycleResponse(DeviceLifecycleState state, String logicalName, boolean manualMode, String deviceType) {
        this.state = state;
        this.logicalName = logicalName;
        this.manualMode = manualMode;
        this.deviceType = deviceType;
    }

    public DeviceLifecycleState getState() {
        return state;
    }

    public String getLogicalName() {
        return logicalName;
    }

    public boolean isManualMode() {
        return manualMode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    @Override
    public String toString() {
        return "DeviceLifecycleResponse{" +
                "state=" + state +
                ", logicalName='" + logicalName + '\'' +
                ", manualMode=" + manualMode +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }
}
