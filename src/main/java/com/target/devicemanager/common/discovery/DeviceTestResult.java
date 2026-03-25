package com.target.devicemanager.common.discovery;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.Map;

/**
 * Result of testing a single JPOS device entry via open/claim/enable/disable/release/close.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeviceTestResult {

    public enum Status {
        OK,
        FAILED
    }

    private final String logicalName;
    private final String category;
    private final Status status;
    private final String error;
    private final Map<String, String> properties;

    public DeviceTestResult(String logicalName, String category, Status status, String error, Map<String, String> properties) {
        this.logicalName = logicalName;
        this.category = category;
        this.status = status;
        this.error = error;
        this.properties = properties != null ? properties : new HashMap<>();
    }

    public static DeviceTestResult ok(String logicalName, String category, Map<String, String> properties) {
        return new DeviceTestResult(logicalName, category, Status.OK, null, properties);
    }

    public static DeviceTestResult failed(String logicalName, String category, String error) {
        return new DeviceTestResult(logicalName, category, Status.FAILED, error, null);
    }

    public String getLogicalName() {
        return logicalName;
    }

    public String getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "DeviceTestResult{" +
                "logicalName='" + logicalName + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}
