package com.aiatss.poc.cache;

import org.springframework.stereotype.Component;

@Component
public class UwCacheCounter {
	public int USING_CACHE_COUNT = 0;
	public int QUERY_DB_COUNT = 0;
}
