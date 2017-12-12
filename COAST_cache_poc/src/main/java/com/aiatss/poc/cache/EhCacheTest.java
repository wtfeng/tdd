package com.aiatss.poc.cache;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.jamonapi.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:spring-test-datasource.xml"})  
public class EhCacheTest {

	@Autowired
	PolicyService policyService;

	@Test
	public void testCachePut_putInOneMethodGetInAnotherWithoutSameKey() {
		long id = 123L;
		Policy policyById = policyService.getPolicyById2(id);
		Policy expectedPolicy = policyService.getPolicyById(id);
		assertNotEquals(policyById, expectedPolicy);
	}
	
	@Test
	public void testCachePut_putInOneMethodGetInAnotherMethodWithTheSameCacheAndKey() {
		long id = 123L;
		Policy policyById = policyService.getPolicyById3(id);
		Policy expectedPolicy = policyService.getPolicyById4(id);
		assertEquals(policyById, expectedPolicy);
	}
	
	
	@Test
	public void testCacheEvict() {
		long id = 123L;
		Policy policyById = policyService.getPolicyById3(id);
		policyService.updatePolicy(id);
		Policy expectedPolicy = policyService.getPolicyById4(id);
		assertNotEquals(policyById, expectedPolicy);
	}
	
	@Test
	public void testCacheable() {
		
		long id = 123L;
		Policy policyById = policyService.getPolicyById(id);
		Policy expectedPolicy = policyService.getPolicyById(id);
		assertEquals(policyById, expectedPolicy);
		
	}

}
