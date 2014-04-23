package com.dubkov.notification.response;

import com.dubkov.notification.response.WinPhoneResponseCode.DeviceConnectionStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.NotificationStatus;
import com.dubkov.notification.response.WinPhoneResponseCode.SubscriptionStatus;

public class WinPhoneResponse {

	private final int code;
	private final NotificationStatus notificationStatus;
	private final DeviceConnectionStatus deviceConnectionStatus;
	private final SubscriptionStatus subscriptionStatus;
	
	public WinPhoneResponse(int code, NotificationStatus notificationStatus,
			DeviceConnectionStatus deviceConnectionStatus,
			SubscriptionStatus subscriptionStatus) {
		this.code = code;
		this.notificationStatus = notificationStatus;
		this.deviceConnectionStatus = deviceConnectionStatus;
		this.subscriptionStatus = subscriptionStatus;
	}
	
	public int getCode() {
		return code;
	}

	public NotificationStatus getNotificationStatus() {
		return notificationStatus;
	}

	public DeviceConnectionStatus getDeviceConnectionStatus() {
		return deviceConnectionStatus;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	@Override
	public String toString() {
		return "WinPhoneResponse [code=" + code + ", notificationStatus="
				+ notificationStatus + ", deviceConnectionStatus="
				+ deviceConnectionStatus + ", subscriptionStatus="
				+ subscriptionStatus + "]";
	}
}
