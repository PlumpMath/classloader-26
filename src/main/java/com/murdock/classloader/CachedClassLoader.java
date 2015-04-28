package com.murdock.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 构造一个ClassLoader， 先从Map中loadClass，然后再进行local加载，最后执行原生的loadClass
 * 
 * </pre>
 * 
 * 
 * @author weipeng2k 2015年2月25日 上午10:39:39
 */
public class CachedClassLoader extends URLClassLoader {

	private final Map<String, Class<?>>	CACHE		= new ConcurrentHashMap<String, Class<?>>();

	private String						name;

	private boolean						localFirst	= true;

	public CachedClassLoader(URL[] urls) {
		super(urls, null);
	}
	
	public CachedClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println(this.name + " try load " + name);
		Class<?> clazz = CACHE.get(name);
		if (clazz != null) {
			System.out.println(this.name + " got " + name  + " in cache");
			return clazz;
		}

		if (localFirst) {
			try {
				clazz = findClass(name);
				if (clazz != null) {
					System.out.println(this.name + " got " + name  + " in local");
					return clazz;
				}
			} catch (ClassNotFoundException ex) {

			}

			System.out.println(this.name + " try load " + name  + " in parent");
			return super.loadClass(name);
		} else {
			System.out.println(this.name + " try load " + name  + " in parent");
			return super.loadClass(name);
		}
	}

	public Class<?> defineClassFromFile(String name, byte[] bytes) {
		return defineClass(name, bytes, 0, bytes.length);
	}

	/**
	 * 将class类名和实例放到cache中
	 * 
	 * @param name
	 * @param clazz
	 */
	public final void injectClass(String name, Class<?> clazz) {
		if (name != null && clazz != null) {
			CACHE.put(name, clazz);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public Class<?> findLoadedClass1(final String name) {
		return this.findLoadedClass(name);
	}

	public void setLocalFirst(boolean localFirst) {
		this.localFirst = localFirst;
	}
}
