package com.aerospike.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aerospike.AerospikeError;
import com.aerospike.client.query.IndexType;
import com.aerospike.models.UserCredential;

@Service("userCredentialService")
public class UserCredentialServiceImpl implements UserCredentialService {

	final static Logger logger = Logger.getLogger(UserCredentialServiceImpl.class);
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Override
	public void save(UserCredential userCredential) {
		try{
			userCredentialRepository.createIndex(UserCredential.class, "UserCredential_userName_index", "userName", IndexType.STRING);
			userCredentialRepository.createIndex(UserCredential.class, "UserCredential_email_index", "email", IndexType.STRING);
			userCredentialRepository.createIndex(UserCredential.class, "UserCredential_token_index", "token", IndexType.STRING);
		
			userCredentialRepository.save(userCredential);
		}catch(AerospikeError e){
			logger.fatal("error while save user credential in aerospike.");
			throw new AerospikeError(e);
		}
	}

	@Override
	public UserCredential findById(String id) {
		try{
			return userCredentialRepository.findOne(id);
		}catch(AerospikeError e){
			logger.fatal("error while getting user credential by id from aerospike.");
			throw new AerospikeError(e);
		}
	}

	@Override
	public List<UserCredential> get() {
		try{
			return (List<UserCredential>)userCredentialRepository.findAll();
		}catch(AerospikeError e){
			logger.fatal("error while getting users credential  from aerospike.");
			throw new AerospikeError(e);
		}
	}

	@Override
	public List<UserCredential> findByUserName(String userName) {
		
		try{
			return userCredentialRepository.findByUserName(userName);
		}catch(AerospikeError e){
			logger.fatal("error while getting user credential by userName  from aerospike.");
			throw new AerospikeError(e);
		}
	}

	@Override
	public List<UserCredential> findByEmail(String email) {
		try{
			return userCredentialRepository.findByEmail(email);
		}catch(AerospikeError e){
			logger.fatal("error while getting user credential by email  from aerospike.");
			throw new AerospikeError(e);
		}
	}

	@Override
	public List<UserCredential> findByToken(String token) {
			try{
				return userCredentialRepository.findByToken(token);
			}catch(AerospikeError e){
				logger.fatal("error while getting user credential by token from aerospike.");
				throw new AerospikeError(e);
			}
	}

	@Override
	public List<UserCredential> findByUserNameAndEmail(String userName, String email) {
			try{
				return userCredentialRepository.findByUserNameAndEmail(userName, email);
			}catch(AerospikeError e){
				logger.fatal("error while getting user credential by userName and email from aerospike.");
				throw new AerospikeError(e);
			}
	}

	@Override
	public List<UserCredential> deleteByUserName(String userName) {
			try{
				return userCredentialRepository.deleteByUserName(userName);
			}catch(AerospikeError e){
				logger.fatal("error while deleteing user credential by userName  from aerospike.");
				throw new AerospikeError(e);
			}
	}

	@Override
	public List<UserCredential> deleteByUserNameAndEmail(String userName, String email) {
			try{
				return userCredentialRepository.deleteByUserNameAndEmail(userName, email);
			}catch(AerospikeError e){
				logger.fatal("error while deleteing user credential by userName and email from aerospike.");
				throw new AerospikeError(e);
			}
	}

	@Override
	public List<UserCredential> deleteByEmail(String email) {
			try{
				return userCredentialRepository.deleteByEmail(email);
			}catch(AerospikeError e){
				logger.fatal("error while deleteing user credential by email from aerospike.");
				throw new AerospikeError(e);
			}
	}

	@Override
	public List<UserCredential> deleteByToken(String token) {
		try{
			return userCredentialRepository.deleteByToken(token);
		}catch(AerospikeError e){
			logger.fatal("error while deleteing user credential by token from aerospike.");
			throw new AerospikeError(e);
		}
	}
}
