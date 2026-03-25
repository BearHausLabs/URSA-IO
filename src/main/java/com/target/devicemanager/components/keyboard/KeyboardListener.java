package com.target.devicemanager.components.keyboard;

import com.target.devicemanager.common.DeviceListener;
import com.target.devicemanager.common.EventSynchronizer;
import jpos.JposConst;

public class KeyboardListener extends DeviceListener {

    public KeyboardListener(EventSynchronizer eventSynchronizer) {
        super(eventSynchronizer);
    }

    @Override
    protected boolean isFailureStatus(int status) {
        switch (status) {
            case JposConst.JPOS_SUE_POWER_ONLINE:
                return false;
            default:
                return true;
        }
    }
}
