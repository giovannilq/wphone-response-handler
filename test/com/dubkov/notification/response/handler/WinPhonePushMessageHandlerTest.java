package com.dubkov.notification.response.handler;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dubkov.notification.response.DeleteMessageResponse;
import com.dubkov.notification.response.GoodMessageResponse;
import com.dubkov.notification.response.TimeWaitMessageResponse;
import com.dubkov.notification.response.UnknownMessageResponse;
import com.dubkov.notification.response.WinPhoneResponse;
import com.dubkov.notification.response.WinPhoneResponseCode.DeviceConnectionStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.NotificationStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.SubscriptionStatus;

public class WinPhonePushMessageHandlerTest {

	@Test
	public void testHandle() {
		WinPhonePushMessageHandler handler = new WinPhonePushMessageHandler();
		assertTrue(handler.handle(new WinPhoneResponse(200, NotificationStatus.RECEIVED, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.ACTIVE)) instanceof GoodMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(200, NotificationStatus.RECEIVED, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE)) instanceof GoodMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(200, NotificationStatus.QUEUE_FULL, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.ACTIVE)) instanceof TimeWaitMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(200, NotificationStatus.QUEUE_FULL, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE)) instanceof TimeWaitMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(200, NotificationStatus.DROPPED, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE)) instanceof UnknownMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(404, NotificationStatus.DROPPED, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.EXPIRED)) instanceof DeleteMessageResponse);
		assertTrue(handler.handle(new WinPhoneResponse(503, NotificationStatus.NOT_AVAILABLE, DeviceConnectionStatus.NOT_AVAILABLE, SubscriptionStatus.NOT_AVAILABLE)) instanceof TimeWaitMessageResponse);
	}

}
