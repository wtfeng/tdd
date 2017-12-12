Feature: Cache annotation Test 

Scenario: The same policy Id again should be able to use cache 
	Given policy IDs 
		| 1  |  
		| 2  |  
		| 3  |  
		| 4  |  
		| 5  |  
	When call the cached method to get Policy 
	Then the same policy will be returned when ID is the same
	
Scenario: With 2 cache servers, one is update the cache, another should receive the evict at the same time. 
	Given policy IDs 
		| 1  |  
		| 2  |  
		| 3  |  
		| 4  |  
		| 5  |  
	When call the cached method to get Policy 
	Then the same policy will be returned when ID is the same