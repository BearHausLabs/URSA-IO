package com.target.devicemanager.components.keylock.entities;

import jpos.KeylockConst;

/**
 * Maps JPOS Keylock position integer constants to a readable enum.
 * Encore used keylock positions to control register access:
 *   LOCK = register locked, NORMAL = cashier mode, SUPERVISOR = admin mode.
 */
public enum KeyPosition {
    LOCK(KeylockConst.LOCK_KP_LOCK),
    NORMAL(KeylockConst.LOCK_KP_NORM),
    SUPERVISOR(KeylockConst.LOCK_KP_SUPR),
    UNKNOWN(-1);

    private final int jposValue;

    KeyPosition(int jposValue) {
        this.jposValue = jposValue;
    }

    public int getJposValue() {
        return jposValue;
    }

    /**
     * Maps a JPOS integer key position constant to the corresponding enum value.
     * Returns UNKNOWN for unrecognized position codes.
     */
    public static KeyPosition fromJposValue(int jposValue) {
        for (KeyPosition position : values()) {
            if (position.jposValue == jposValue) {
                return position;
            }
        }
        return UNKNOWN;
    }
}
