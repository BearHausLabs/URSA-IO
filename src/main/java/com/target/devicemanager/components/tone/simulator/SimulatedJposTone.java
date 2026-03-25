package com.target.devicemanager.components.tone.simulator;

import com.target.devicemanager.common.SimulatorState;
import jpos.JposConst;
import jpos.ToneIndicator;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import org.springframework.context.annotation.Profile;

import java.util.Vector;

@Profile("local")
public class SimulatedJposTone extends ToneIndicator {
    private SimulatorState simulatorState;

    public SimulatedJposTone() {
        simulatorState = SimulatorState.ONLINE;
    }

    void setState(SimulatorState simulatorState) {
        this.simulatorState = simulatorState;
        triggerStatusUpdateEvent();
    }

    @Override
    public void sound(int numberOfCycles, int interToneWait) {
        // In simulation mode, just log that a tone was requested
        // No actual hardware to play through
    }

    private void triggerStatusUpdateEvent() {
        int status = simulatorState == SimulatorState.ONLINE
                ? JposConst.JPOS_SUE_POWER_ONLINE
                : JposConst.JPOS_SUE_POWER_OFF_OFFLINE;

        StatusUpdateEvent statusUpdateEvent = new StatusUpdateEvent(this, status);

        ((Vector<StatusUpdateListener>) statusUpdateListeners).forEach(listener ->
                listener.statusUpdateOccurred(statusUpdateEvent)
        );
    }

    @Override
    public int getState() {
        return simulatorState == SimulatorState.ONLINE ? JposConst.JPOS_S_IDLE : JposConst.JPOS_S_CLOSED;
    }

    @Override
    public boolean getDeviceEnabled() {
        return false;
    }

    @Override
    public void setDeviceEnabled(boolean value) {
        // doNothing
    }

    @Override
    public void close() {
        // doNothing
    }
}
