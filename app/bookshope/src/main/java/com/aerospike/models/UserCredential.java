package com.aerospike.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class UserCredential {

	@Id
	private String id;
	private String userName;
    private String email;
    private String token;
    private Boolean tokenStatus;
    private Date createdAt;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(Boolean tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
