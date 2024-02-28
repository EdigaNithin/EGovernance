package com.governance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_request")
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;

	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;

	private String serviceName;
	private String applicationStatus;
	private String invitationCardUploaded;

	public ServiceRequest() {

	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getInvitationCardUploaded() {
		return invitationCardUploaded;
	}

	public void setInvitationCardUploaded(String invitationCardUploaded) {
		this.invitationCardUploaded = invitationCardUploaded;
	}
}
