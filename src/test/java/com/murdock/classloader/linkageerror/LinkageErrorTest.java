package com.murdock.classloader.linkageerror;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

public class LinkageErrorTest {

	@Test
	public void test() throws Exception {
		
		URLClassLoader cl1 = new URLClassLoader(new URL[] {new File("target/test-classes").toURI().toURL()}, null) {

			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				if ("com.murdock.classloader.linkageerror.HandleUtils".equals(name)) {
					return ClassLoader.getSystemClassLoader().loadClass(name);
				}
				
				if ("com.murdock.classloader.linkageerror.Param".equals(name)) {
					return ClassLoader.getSystemClassLoader().loadClass(name);
				}
				
				return super.loadClass(name);
			}
			
		};
		
		ClassLoader.getSystemClassLoader().loadClass("com.murdock.classloader.linkageerror.Param2");
		HandleUtils hu = (HandleUtils) cl1.loadClass("com.murdock.classloader.linkageerror.HandleUtils").newInstance();
		hu.m((Param) cl1.loadClass("com.murdock.classloader.linkageerror.Param2").newInstance());
	}

}
