package com.target.devicemanager.components.msr.simulator;

import com.target.devicemanager.common.SimulatorState;
import com.target.devicemanager.components.msr.entities.MsrData;
import jpos.JposConst;
import jpos.MSR;
import jpos.events.DataEvent;
import jpos.events.DataListener;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import org.springframework.context.annotation.Profile;

import java.nio.charset.Charset;
import java.util.Vector;

@Profile("local")
public class SimulatedJposMsr extends MSR {
    private SimulatorState simulatorState;
    private MsrData lastSwipeData;

    public SimulatedJposMsr() {
        simulatorState = SimulatorState.ONLINE;
        this.lastSwipeData = new MsrData(
                "%B1234567890123456^DOE/JOHN^2512101000000000000000000000000?",
                ";1234567890123456=25121010000000000000?",
                null,
                null
        );
    }

    void swipeCard(MsrData msrData) {
        this.lastSwipeData = msrData;
        triggerDataEvent();
    }

    void setState(SimulatorState simulatorState) {
        this.simulatorState = simulatorState;
        triggerStatusUpdateEvent();
    }

    private void triggerDataEvent() {
        DataEvent dataEvent = new DataEvent(this, JposConst.JPOS_SUCCESS);

        for (Object object : dataListeners) {
            DataListener dataListener = (DataListener) object;
            dataListener.dataOccurred(dataEvent);
        }
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
    public byte[] getTrack1Data() {
        return lastSwipeData.track1 != null ? lastSwipeData.track1.getBytes(Charset.defaultCharset()) : new byte[0];
    }

    @Override
    public byte[] getTrack2Data() {
        return lastSwipeData.track2 != null ? lastSwipeData.track2.getBytes(Charset.defaultCharset()) : new byte[0];
    }

    @Override
    public byte[] getTrack3Data() {
        return lastSwipeData.track3 != null ? lastSwipeData.track3.getBytes(Charset.defaultCharset()) : new byte[0];
    }

    @Override
    public byte[] getTrack4Data() {
        return lastSwipeData.track4 != null ? lastSwipeData.track4.getBytes(Charset.defaultCharset()) : new byte[0];
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
    public void setAutoDisable(boolean value) {
        // doNothing
    }

    @Override
    public void setDecodeData(boolean value) {
        // doNothing
    }

    @Override
    public void setDataEventEnabled(boolean value) {
        // doNothing
    }

    @Override
    public void close() {
        // doNothing
    }
}
