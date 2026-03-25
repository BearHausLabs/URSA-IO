package com.target.devicemanager.common.discovery;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;

/**
 * Response from device discovery -- maps device category to list of available logical names.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DiscoveryResponse {

    private final Map<String, List<String>> devices;

    public DiscoveryResponse(Map<String, List<String>> devices) {
        this.devices = devices;
    }

    public Map<String, List<String>> getDevices() {
        return devices;
    }
}
