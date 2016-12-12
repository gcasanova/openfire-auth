package com.i7.openfire.config;

import java.util.Set;

import com.google.common.collect.Sets;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class DataConfig {
	
	private static volatile DataConfig instance;
	
	private JedisCluster jedis;
	
	private DataConfig() {
		Properties properties = Properties.getInstance();
		
		Set<HostAndPort> jedisClusterNodes = Sets.newHashSet();
		for (String node : properties.getRedisNodes())
			jedisClusterNodes.add(new HostAndPort(node.split(":")[0], Integer.valueOf(node.split(":")[1])));
		
		if (properties.getRedisTimeOut() > 0) {
			if (properties.getRedisMaxRedirects() > 0) {
				jedis = new JedisCluster(jedisClusterNodes, properties.getRedisTimeOut(), properties.getRedisMaxRedirects());
			} else {
				jedis = new JedisCluster(jedisClusterNodes, properties.getRedisTimeOut());
			}
		} else {
			jedis = new JedisCluster(jedisClusterNodes);
		}
	}
	
	public static DataConfig getInstance() {
		if (instance == null) {
			synchronized(DataConfig.class) {
				if (instance == null)
					instance = new DataConfig();
			}
		}
		return instance;
	}

	public JedisCluster getJedis() {
		return jedis;
	}
}
