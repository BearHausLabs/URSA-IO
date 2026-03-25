package com.target.devicemanager.components.cashdrawer;

import com.target.devicemanager.common.entities.DeviceError;
import com.target.devicemanager.common.entities.DeviceException;
import com.target.devicemanager.common.entities.DeviceHealth;
import com.target.devicemanager.common.entities.DeviceHealthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CashDrawerControllerTest {

    private CashDrawerController cashDrawerController;

    @Mock
    private CashDrawerManager mockCashDrawerManager;

    @BeforeEach
    public void testInitialize() {
        cashDrawerController = new CashDrawerController(mockCashDrawerManager);
    }

    @Test
    public void ctor_WhenCashDrawerManagerIsNull_ThrowsException() {
        try {
            new CashDrawerController(null);
        } catch (IllegalArgumentException iae) {
            assertEquals("cashDrawerManager cannot be null", iae.getMessage());
            return;
        }

        fail("Expected Exception, but got none");
    }

    @Test
    public void ctor_WhenCashDrawerManagerIsNew_DoesNotThrowException() {
        try {
            new CashDrawerController(mockCashDrawerManager);
        } catch (Exception exception) {
            fail("Existing Manager Argument should not result in an Exception");
        }
    }

    @Test
    public void openCashDrawer_NoDrawerId_CallsThroughToCashDrawerManager() throws DeviceException {
        //arrange

        //act
        cashDrawerController.openCashDrawer(null);

        //assert
        verify(mockCashDrawerManager).openCashDrawer(1);
    }

    @Test
    public void openCashDrawer_WithDrawerId_CallsThroughToCashDrawerManager() throws DeviceException {
        //arrange

        //act
        cashDrawerController.openCashDrawer(2);

        //assert
        verify(mockCashDrawerManager).openCashDrawer(2);
    }

    @Test
    public void openCashDrawer_WhenThrowsError() throws DeviceException {
        //arrange
        doThrow(new DeviceException(DeviceError.DEVICE_BUSY)).when(mockCashDrawerManager).openCashDrawer(1);

        //act
        try {
            cashDrawerController.openCashDrawer(null);
        }

        //assert
        catch(DeviceException deviceException) {
            verify(mockCashDrawerManager).openCashDrawer(1);
            assertEquals(DeviceError.DEVICE_BUSY, deviceException.getDeviceError());
            return;
        }
        fail("Expected Exception, but got none.");
    }

    @Test
    public void reconnect_NoDrawerId_CallsThroughToCashDrawerManager() throws DeviceException {
        //arrange

        //act
        try {
            cashDrawerController.reconnect(null);
        } catch (DeviceException deviceException) {
            fail("cashDrawerController.reconnect() should not result in an Exception");
        }

        //assert
        verify(mockCashDrawerManager).reconnectDevice();
    }

    @Test
    public void reconnect_WithDrawerId_CallsThroughToCashDrawerManager() throws DeviceException {
        //arrange

        //act
        try {
            cashDrawerController.reconnect(1);
        } catch (DeviceException deviceException) {
            fail("cashDrawerController.reconnect() should not result in an Exception");
        }

        //assert
        verify(mockCashDrawerManager).reconnectDevice(1);
    }

    @Test
    public void reconnect_WhenThrowsError() throws DeviceException {
        //arrange
        doThrow(new DeviceException(DeviceError.DEVICE_BUSY)).when(mockCashDrawerManager).reconnectDevice();

        //act
        try {
            cashDrawerController.reconnect(null);
        }

        //assert
        catch(DeviceException deviceException) {
            verify(mockCashDrawerManager).reconnectDevice();
            assertEquals(DeviceError.DEVICE_BUSY, deviceException.getDeviceError());
            return;
        }
        fail("Expected Exception, but got none.");
    }

    @Test
    public void getHealth_NoDrawerId_ReturnsAllHealthFromManager() {
        //arrange
        List<DeviceHealthResponse> expected = List.of(new DeviceHealthResponse("cashDrawer", DeviceHealth.READY));
        when(mockCashDrawerManager.getAllHealth()).thenReturn(expected);

        //act
        ResponseEntity<List<DeviceHealthResponse>> actual = cashDrawerController.getHealth(null);

        //assert
        assertEquals(expected, actual.getBody());
        verify(mockCashDrawerManager).getAllHealth();
    }

    @Test
    public void getHealth_WithDrawerId_ReturnsSpecificHealthFromManager() {
        //arrange
        DeviceHealthResponse expected = new DeviceHealthResponse("cashDrawer", DeviceHealth.READY);
        when(mockCashDrawerManager.getHealth(1)).thenReturn(expected);

        //act
        ResponseEntity<List<DeviceHealthResponse>> actual = cashDrawerController.getHealth(1);

        //assert
        assertEquals(1, actual.getBody().size());
        assertEquals(expected, actual.getBody().get(0));
        verify(mockCashDrawerManager).getHealth(1);
    }

    @Test
    public void getStatus_ReturnsStatusFromManager() {
        //arrange
        DeviceHealthResponse expected = new DeviceHealthResponse("cashDrawer", DeviceHealth.READY);
        when(mockCashDrawerManager.getStatus()).thenReturn(expected);

        //act
        DeviceHealthResponse actual = cashDrawerController.getStatus();

        //assert
        assertEquals(expected, actual);
        verify(mockCashDrawerManager).getStatus();
    }
}
