package com.target.devicemanager.components.keylock;

import com.target.devicemanager.common.DeviceListener;
import com.target.devicemanager.common.EventSynchronizer;
import jpos.JposConst;
import jpos.KeylockConst;

public class KeylockListener extends DeviceListener {

    public KeylockListener(EventSynchronizer eventSynchronizer) {
        super(eventSynchronizer);
    }

    @Override
    protected boolean isFailureStatus(int status) {
        switch (status) {
            case KeylockConst.LOCK_KP_LOCK:
            case KeylockConst.LOCK_KP_NORM:
            case KeylockConst.LOCK_KP_SUPR:
            case JposConst.JPOS_PS_ONLINE:
                return false;
            default:
                return true;
        }
    }
}
