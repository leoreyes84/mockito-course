package com.in28minutes.powermock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.in28minutes.data.api.TodoService;

public class MockingStaticMethodTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	// TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {

		List<Integer> stats = Arrays.asList(1,2,3);

		when(dependency.retrieveAllStats()).thenReturn(stats);

		systemUnderTest.methodCallingAStaticMethod();
	}

	

}
