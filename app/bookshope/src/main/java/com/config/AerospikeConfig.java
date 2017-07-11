package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;

@Configuration
@EnableAerospikeRepositories(basePackages = "com.aerospike")
public class AerospikeConfig {

	public @Bean(destroyMethod = "close") AerospikeClient aerospikeClient() {
        
        ClientPolicy policy = new ClientPolicy();
        policy.failIfNotConnected = true;
        policy.timeout = 2000;
        
        AerospikeClient ac = new AerospikeClient(policy,"127.0.0.1", 3000);
        
        return ac;
    }
    
    @Bean
    public AerospikeTemplate aerospikeTemplate() {
    	
        return new AerospikeTemplate(aerospikeClient(), "test");
    }
}
