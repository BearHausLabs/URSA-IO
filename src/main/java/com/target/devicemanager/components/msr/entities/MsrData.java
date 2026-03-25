package com.target.devicemanager.components.msr.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MsrData {
    public String track1;
    public String track2;
    public String track3;
    public String track4;

    public MsrData() {
    }

    public MsrData(String track1, String track2, String track3, String track4) {
        this.track1 = track1;
        this.track2 = track2;
        this.track3 = track3;
        this.track4 = track4;
    }

    @Override
    public String toString() {
        return "MsrData{" +
                "track1='" + (track1 != null ? "***" : "null") + '\'' +
                ", track2='" + (track2 != null ? "***" : "null") + '\'' +
                ", track3='" + (track3 != null ? "***" : "null") + '\'' +
                ", track4='" + (track4 != null ? "***" : "null") + '\'' +
                '}';
    }
}
