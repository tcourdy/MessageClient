package main;

public class Notification {
	private Integer endpointId;
	private NotificationType notificationType;
	private String payload;
	
	
	public Notification() {
		
	}
	public Integer getEndpointId() {
		return endpointId;
	}
	public void setEndpointId(Integer endpointId) {
		this.endpointId = endpointId;
	}
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}

}
