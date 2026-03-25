package com.target.devicemanager.common;

/**
 * Represents the current JPOS lifecycle state of a device.
 * Devices progress through: CLOSED -> OPENED -> CLAIMED -> ENABLED
 * and reverse back down on disconnect.
 */
public enum DeviceLifecycleState {
    CLOSED,
    OPENED,
    CLAIMED,
    ENABLED
}
