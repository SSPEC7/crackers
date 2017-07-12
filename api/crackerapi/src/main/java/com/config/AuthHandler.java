package com.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Constants;
import com.exception.BookException;
import com.google.gson.Gson;
import com.models.UserLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Configuration
public class AuthHandler {

	@Bean(initMethod = "init", destroyMethod = "cleanup" )
	public TokenHandlerImpl tokenHandlerImpl(){
		return new TokenHandlerImpl();
	}
}

interface TokenHandler{
	
	int isTokenExpired(String token);
}

class TokenHandlerImpl implements TokenHandler {

	private MongoClient mongo;
	private DB db; 
	private DBCollection collection;
	
	public void init() throws UnknownHostException {
		
		this.mongo = new MongoClient( "127.0.0.1" , 27017 );
		this.db = this.mongo.getDB("demo-test");
		this.collection = this.db.getCollection("users_log");
		
		System.out.println("Init Token");
	}
	
	@Override
	public int isTokenExpired(String token) {
		try{
			if(token!=null){
				UserLog userLog = this.getUserByToken(token);
				
				if(userLog!=null){
					return userLog.tokenStatus();
				}
			}
			return Constants.BAD_TOKEN;
		}catch(Exception e){
			String message = String.format("Error while checking isTokenExpired @TokenHandler.");
			throw new BookException(message, e);
		}
	}
	
	private UserLog getUserByToken(String token){
		try{
			
			Gson gson = new Gson();
			UserLog userLog = null;
			BasicDBObject query = new BasicDBObject("token", token);
			DBCursor cursor = this.collection.find(query);
			Object data = null;
			try{
			while(cursor.hasNext()) {
				data = cursor.next();
			   }
			}finally{
				cursor.close();
			}
			
			if(data!=null){
				userLog = gson.fromJson(gson.toJson(data), UserLog.class);
			}
			
			return userLog;
			
		}catch(Exception e){
			String message = String.format("Error while fetching user by token @TokenHandler.");
			throw new BookException(message, e);
		}
	}
   
	public void cleanup() {
		System.out.println("cleanup Token");
		mongo.close();
   }
}