package com.aerospike;

public class AerospikeError extends RuntimeException {

	private static final long serialVersionUID = 2771174581631905388L;
	
	public AerospikeError() {}
	
	public AerospikeError(String error) {
		super(error);
	}
	
	public AerospikeError(Throwable throwable){
		super(throwable);
	}
	
	public AerospikeError(String error,Throwable throwable) {
		super(error,throwable);
	}
	
	public AerospikeError(String error,Exception e) {
		super(error,e);
	}
}
