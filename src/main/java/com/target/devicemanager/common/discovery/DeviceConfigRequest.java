package com.target.devicemanager.common.discovery;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * Request body for POST /v1/discovery/config -- accepts a device configuration.
 * This is how URSA admin UI pushes config after admin picks devices.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeviceConfigRequest {

    private DeviceEntry printer;
    private CashDrawerConfigEntry cashdrawer;
    private ScannerConfigEntry scanner;
    private DeviceEntry linedisplay;
    private DeviceEntry micr;
    private DeviceEntry scale;
    private DeviceEntry msr;
    private DeviceEntry tone;
    private DeviceEntry keylock;
    private DeviceEntry keyboard;

    public DeviceEntry getPrinter() {
        return printer;
    }

    public void setPrinter(DeviceEntry printer) {
        this.printer = printer;
    }

    public CashDrawerConfigEntry getCashdrawer() {
        return cashdrawer;
    }

    public void setCashdrawer(CashDrawerConfigEntry cashdrawer) {
        this.cashdrawer = cashdrawer;
    }

    public ScannerConfigEntry getScanner() {
        return scanner;
    }

    public void setScanner(ScannerConfigEntry scanner) {
        this.scanner = scanner;
    }

    public DeviceEntry getLinedisplay() {
        return linedisplay;
    }

    public void setLinedisplay(DeviceEntry linedisplay) {
        this.linedisplay = linedisplay;
    }

    public DeviceEntry getMicr() {
        return micr;
    }

    public void setMicr(DeviceEntry micr) {
        this.micr = micr;
    }

    public DeviceEntry getScale() {
        return scale;
    }

    public void setScale(DeviceEntry scale) {
        this.scale = scale;
    }

    public DeviceEntry getMsr() {
        return msr;
    }

    public void setMsr(DeviceEntry msr) {
        this.msr = msr;
    }

    public DeviceEntry getTone() {
        return tone;
    }

    public void setTone(DeviceEntry tone) {
        this.tone = tone;
    }

    public DeviceEntry getKeylock() {
        return keylock;
    }

    public void setKeylock(DeviceEntry keylock) {
        this.keylock = keylock;
    }

    public DeviceEntry getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(DeviceEntry keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Simple device entry: logical name + enabled flag.
     */
    public static class DeviceEntry {
        private String logicalName;
        private boolean enabled = true;

        public String getLogicalName() {
            return logicalName;
        }

        public void setLogicalName(String logicalName) {
            this.logicalName = logicalName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Cash drawer config with dynamic list of drawers.
     */
    public static class CashDrawerConfigEntry {
        private List<DrawerEntry> drawers;

        public List<DrawerEntry> getDrawers() {
            return drawers;
        }

        public void setDrawers(List<DrawerEntry> drawers) {
            this.drawers = drawers;
        }
    }

    public static class DrawerEntry {
        private String logicalName;
        private boolean enabled = true;

        public String getLogicalName() {
            return logicalName;
        }

        public void setLogicalName(String logicalName) {
            this.logicalName = logicalName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Scanner config with flatbed/handheld sub-devices.
     */
    public static class ScannerConfigEntry {
        private DeviceEntry flatbed;
        private DeviceEntry handheld;

        public DeviceEntry getFlatbed() {
            return flatbed;
        }

        public void setFlatbed(DeviceEntry flatbed) {
            this.flatbed = flatbed;
        }

        public DeviceEntry getHandheld() {
            return handheld;
        }

        public void setHandheld(DeviceEntry handheld) {
            this.handheld = handheld;
        }
    }
}
