package com.aerospike.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.aerospike.repository.AerospikeRepository;
import com.aerospike.models.UserCredential;

public interface UserCredentialRepository extends AerospikeRepository<UserCredential, String> {

	List<UserCredential> findByUserName(String userName);
	List<UserCredential> findByUserNameStartsWith(String prefix);
    List<UserCredential> findByUserNameEndsWith(String postfix);
    List<UserCredential> findByUserNameLike(String userName);
    List<UserCredential> findByUserNameIn(String... userNames);
    List<UserCredential> findByUserNameNotIn(Collection<String> userNames);
    List<UserCredential> findByCreatedAtLessThan(Date date);
    List<UserCredential> findByCreatedAtGreaterThan(Date date);
    List<UserCredential> findByCreatedAtBefore(Date date);
    List<UserCredential> findByCreatedAtAfter(Date date);
    List<UserCredential> findByUserNameIgnoreCase(String userName);
    List<UserCredential> findByUserNameNotIgnoreCase(String userName);
    List<UserCredential> findByUserNameStartingWithIgnoreCase(String userName);
    List<UserCredential> findByUserNameEndingWithIgnoreCase(String userName);
    List<UserCredential> findByUserNameContainingIgnoreCase(String userName);
    List<UserCredential> findByUserNameAndEmail(String userName, String email);
    List<UserCredential> deleteByUserName(String userName);
    List<UserCredential> deleteByUserNameAndEmail(String userName, String email);
    
    
    List<UserCredential> findByEmail(String email);
    List<UserCredential> deleteByEmail(String email);
    
    List<UserCredential> findByToken(String token);
    List<UserCredential> deleteByToken(String token);
}
