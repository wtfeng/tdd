package com.aiatss.poc.cache;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:spring-test-datasource.xml"}) 
public class CacheTestStep {
	private List<Long> asList;

	@Autowired
	PolicyService policyService;
	
	@Given("^policy IDs$")
	public List<Long> given(DataTable table)  {
		asList = table.asList(Long.class);
		return asList;
	}
	
	@When("^call the cached method to get Policy$")
	public void when1() {
		for (Long id : asList) {
			Policy policy = policyService.getPolicyById(id);
			Policy expectedPolicy = policyService.getPolicyById(id);
			assertEquals(policy,expectedPolicy);
		}
	}
	
	@Then("^the same policy will be returned when ID is the same$")
	public void then1() {
		
	}
}
