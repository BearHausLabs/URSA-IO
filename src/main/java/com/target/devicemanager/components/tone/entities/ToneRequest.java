package com.target.devicemanager.components.tone.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ToneRequest {
    /** Number of tone cycles to play. */
    public int numberOfCycles = 1;

    /** Pause between tones in milliseconds. */
    public int interToneWait = 0;

    /** Duration of first tone in milliseconds. */
    public int tone1Duration = 200;

    /** Frequency of first tone in Hz. */
    public int tone1Frequency = 1000;

    /** Duration of second tone in milliseconds (0 = no second tone). */
    public int tone2Duration = 0;

    /** Frequency of second tone in Hz. */
    public int tone2Frequency = 0;

    public ToneRequest() {
    }

    public ToneRequest(int numberOfCycles, int interToneWait,
                       int tone1Duration, int tone1Frequency,
                       int tone2Duration, int tone2Frequency) {
        this.numberOfCycles = numberOfCycles;
        this.interToneWait = interToneWait;
        this.tone1Duration = tone1Duration;
        this.tone1Frequency = tone1Frequency;
        this.tone2Duration = tone2Duration;
        this.tone2Frequency = tone2Frequency;
    }

    @Override
    public String toString() {
        return "ToneRequest{" +
                "cycles=" + numberOfCycles +
                ", freq=" + tone1Frequency +
                ", dur=" + tone1Duration +
                '}';
    }
}
