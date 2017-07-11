package com.aerospike.services;

import java.util.List;
import com.aerospike.models.UserCredential;

public interface UserCredentialService {

	void save(UserCredential userCredential);
	UserCredential findById(String id);
	List<UserCredential> get();
	
	List<UserCredential> findByUserName(String userName);
	List<UserCredential> findByEmail(String email);
	List<UserCredential> findByToken(String token);
	List<UserCredential> findByUserNameAndEmail(String userName,String email);
	
	List<UserCredential> deleteByUserName(String userName);
    List<UserCredential> deleteByUserNameAndEmail(String userName, String email);
    List<UserCredential> deleteByEmail(String email);
    List<UserCredential> deleteByToken(String token);
}
