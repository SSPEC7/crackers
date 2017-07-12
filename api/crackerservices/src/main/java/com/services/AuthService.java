package com.services;

import com.models.UserLog;

public interface AuthService {

	UserLog login(UserLog userCredential);
	int isTokenExpired(String token);
	Boolean loggedOut(String token);
}
