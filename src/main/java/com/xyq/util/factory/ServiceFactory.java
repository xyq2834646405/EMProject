package com.xyq.util.factory;

import com.xyq.util.service.proxy.ServiceProxy;

public class ServiceFactory {
	public static <T> T getInstance(Class<T> cls) {
		try {
			return (T) new ServiceProxy().bind(cls.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
}
