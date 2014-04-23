package com.dubkov.notification.response;

public class WinPhoneResponseCode {

	public enum Response {
		DELETE, GOOD, TIME_WAIT, UNKNOWN;
	}

	public enum NotificationStatus {
		NOT_AVAILABLE, RECEIVED, QUEUE_FULL, SUPRESSED, DROPPED, ANY;
	}

	public enum DeviceConnectionStatus {
		NOT_AVAILABLE, DISCONNECTED, CONNECTED, TEMP_DISCONNECTED;
	}

	public enum SubscriptionStatus {
		NOT_AVAILABLE, ACTIVE, EXPIRED;
	}

	private final int code;
	private final NotificationStatus notificationStatus;
	private final DeviceConnectionStatus deviceConnectionStatus;
	private final SubscriptionStatus subscriptionStatus;
	private final Response response;

	public WinPhoneResponseCode(int code,
			NotificationStatus notificationStatus,
			DeviceConnectionStatus deviceConnectionStatus,
			SubscriptionStatus subscriptionStatus, Response response) {
		super();
		this.code = code;
		this.notificationStatus = notificationStatus;
		this.deviceConnectionStatus = deviceConnectionStatus;
		this.subscriptionStatus = subscriptionStatus;
		this.response = response;
	}

	public boolean compare(WinPhoneResponse response) {
		if (response.getCode() != code)
			return false;
		
		
		if (notificationStatus == NotificationStatus.ANY) { 
			if (response.getNotificationStatus() == null || response.getNotificationStatus() == NotificationStatus.ANY)
				return false;
		} else {
			if (response.getNotificationStatus() != notificationStatus)
				return false;
		}
		

		if (response.getDeviceConnectionStatus() != deviceConnectionStatus)
			return false;

		if (response.getSubscriptionStatus() != subscriptionStatus)
			return false;

		return true;
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

	public PushMessageResponse getResponse() {
		switch (response) {
		case DELETE:
			return new DeleteMessageResponse();
		case GOOD:
			return new GoodMessageResponse();
		case TIME_WAIT:
			return new TimeWaitMessageResponse();
		case UNKNOWN:
		default:
			return new UnknownMessageResponse();
		}
	}

	@Override
	public String toString() {
		return "WinPhoneResponseCode [code=" + code + ", notificationStatus="
				+ notificationStatus + ", deviceConnectionStatus="
				+ deviceConnectionStatus + ", subscriptionStatus="
				+ subscriptionStatus + ", response=" + response + "]";
	}

}
