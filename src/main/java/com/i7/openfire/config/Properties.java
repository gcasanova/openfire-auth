package com.i7.openfire.config;

import java.util.Arrays;
import java.util.Set;

import org.jivesoftware.util.JiveGlobals;

import com.google.common.collect.Sets;

public class Properties {

	private static volatile Properties instance;
	
	private String issuer;
    private String domain;
    private String keyPath;
    private int redisTimeOut;
    private int redisMaxRedirects;
    private Set<String> redisNodes;

    private Properties(){
    	domain = JiveGlobals.getProperty(Conf.DOMAIN.toString());
    	keyPath = JiveGlobals.getProperty(Conf.KEY_PATH.toString());
    	issuer = JiveGlobals.getProperty(Conf.ISSUER_NAME.toString());
    	redisTimeOut = Integer.valueOf(JiveGlobals.getProperty(Conf.REDIS_TIMEOUT.toString()));
    	redisMaxRedirects = Integer.valueOf(JiveGlobals.getProperty(Conf.REDIS_MAX_REDIRECTS.toString()));
    	redisNodes = Sets.newHashSet(Arrays.asList(JiveGlobals.getProperty(Conf.REDIS_NODES.toString()).split(",")));
    }
    
    public static Properties getInstance() {
    	if (instance == null) {
    		synchronized (Properties.class) {
    			if (instance == null)
    				instance = new Properties();
			}
    	}
    	return instance;
    }
    
    public String getDomain() {
		return domain;
	}
    
	public Set<String> getRedisNodes() {
		return redisNodes;
	}
	
	public int getRedisTimeOut() {
		return redisTimeOut;
	}

	public int getRedisMaxRedirects() {
		return redisMaxRedirects;
	}

	public String getIssuer() {
		return issuer;
	}
	
	public String getKeyPath() {
		return keyPath;
	}

	private enum Conf {
        DOMAIN("xmpp.domain"),
        KEY_PATH("i7.key.path"),
        ISSUER_NAME("i7.issuer.name"),
        REDIS_NODES("i7.redis.nodes"),
        REDIS_TIMEOUT("i7.redis.timeout"),
        REDIS_MAX_REDIRECTS("i7.redis.max.redirects");

        private final String value;

        Conf(String key) {
            this.value = key;
        }

        @Override
        public String toString(){
            return this.value;
        }
    }
}
