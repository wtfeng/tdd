package com.aiatss.poc.cache;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:com/aiatss/poc/cache" }, glue = { "com.aiatss.poc.cache" })
public class CacheCucumberFunctionIT {

}
