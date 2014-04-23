package com.dubkov.notification.response.handler;

import java.util.ArrayList;
import java.util.Collection;

import com.dubkov.notification.response.PushMessageResponse;
import com.dubkov.notification.response.UnknownMessageResponse;
import com.dubkov.notification.response.WinPhoneResponse;
import com.dubkov.notification.response.WinPhoneResponseCode;
import com.dubkov.notification.response.WinPhoneResponseCode.DeviceConnectionStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.NotificationStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.Response;
import com.dubkov.notification.response.WinPhoneResponseCode.SubscriptionStatus;

public class WinPhonePushMessageHandler {
	
	private Collection<WinPhoneResponseCode> codes = new ArrayList<WinPhoneResponseCode>() {
		private static final long serialVersionUID = 1L;
		{
			add(new WinPhoneResponseCode(200, NotificationStatus.RECEIVED, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.ACTIVE, Response.GOOD));
			add(new WinPhoneResponseCode(200, NotificationStatus.RECEIVED, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE, Response.GOOD));
			add(new WinPhoneResponseCode(200, NotificationStatus.QUEUE_FULL, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.ACTIVE, Response.TIME_WAIT));
			add(new WinPhoneResponseCode(200, NotificationStatus.QUEUE_FULL, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE, Response.TIME_WAIT));
			add(new WinPhoneResponseCode(200, NotificationStatus.ANY, DeviceConnectionStatus.TEMP_DISCONNECTED, SubscriptionStatus.ACTIVE, Response.UNKNOWN));
			add(new WinPhoneResponseCode(404, NotificationStatus.DROPPED, DeviceConnectionStatus.CONNECTED, SubscriptionStatus.EXPIRED, Response.DELETE));
			add(new WinPhoneResponseCode(503, NotificationStatus.NOT_AVAILABLE, DeviceConnectionStatus.NOT_AVAILABLE, SubscriptionStatus.NOT_AVAILABLE, Response.TIME_WAIT));
		}
	};
	
	public PushMessageResponse handle(WinPhoneResponse response) {
		for (WinPhoneResponseCode code : codes) {
			if (code.compare(response))
				return code.getResponse();
		}
		return new UnknownMessageResponse();
	}
}

