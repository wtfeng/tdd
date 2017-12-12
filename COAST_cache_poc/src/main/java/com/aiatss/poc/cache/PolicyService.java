package com.aiatss.poc.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

@Service
@Scope("prototype")
public class PolicyService {

	@Autowired
	private UwCacheCounter counter = null;

	@Cacheable("short_time_cache")
	public Policy getPolicyById(long id) {
		Monitor mon= MonitorFactory.start(this.getClass().getName()+".getPolicyById()");
		Policy policy = new Policy();
		policy.setPolicyId(id);
		mon.stop();
		System.out.println(mon);
		return policy;
	}

	@CachePut("short_time_cache2")
	public Policy getPolicyById2(long id) {
		Policy policy = new Policy();
		policy.setPolicyId(id);
		return policy;
	}

	@CachePut(value = "short_time_cache2", key = "#id")
	public Policy getPolicyById3(long id) {
		Policy policy = new Policy();
		policy.setPolicyId(id);
		return policy;
	}

	@Cacheable(value = "short_time_cache2", key = "#id")
	public Policy getPolicyById4(long id) {
		Policy policy = new Policy();
		policy.setPolicyId(id);
		return policy;
	}

	@CacheEvict(value = "short_time_cache2", key = "#id")
	public void updatePolicy(long id) {

	}
}
