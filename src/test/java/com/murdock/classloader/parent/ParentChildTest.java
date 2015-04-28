package com.murdock.classloader.parent;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.Test;

import com.murdock.classloader.CachedClassLoader;

public class ParentChildTest {

	@Test
	public void test() throws Exception {
		
		// child拥有Parent和Child
		CachedClassLoader child = new CachedClassLoader(new URL[] {
				new File("/Users/weipeng2k/Documents/arena/classloader/target/classes").toURI().toURL(),
				new File("/Users/weipeng2k/Documents/arena/classloader/target/test-classes").toURI().toURL() },
				ClassLoader.getSystemClassLoader().getParent());
		child.setName("child");
		// parent只有Parent
		CachedClassLoader parent = new CachedClassLoader(new URL[] { new File(
				"/Users/weipeng2k/Documents/arena/classloader/target/classes").toURI().toURL() }, child);
		parent.setName("parent");
		
		Class<?> clazz = parent.loadClass("com.murdock.classloader.parent.Child");
		
		// child 接口 ， 属于child
		// 由加载了Child的ClassLoader进行加载
		TestCase.assertEquals(clazz.getInterfaces()[0].getClassLoader(), child);
		
		parent.close();

	}

}
