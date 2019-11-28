package com.in28minutes.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
//		List arrayListSpy = mock(ArrayList.class);
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");
		assertEquals(1, arrayListSpy.size());
		arrayListSpy.remove(0);
		assertEquals(0, arrayListSpy.size());
	}

}
