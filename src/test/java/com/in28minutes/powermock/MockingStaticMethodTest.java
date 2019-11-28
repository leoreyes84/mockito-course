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
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	// TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList();

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		// Then
		assertThat(filteredTodos.size(), is(2));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// Then (similarities)
		verify(todoServiceMock, times(1)).deleteTodos("Learn to Dance");
		verify(todoServiceMock, atLeastOnce()).deleteTodos("Learn to Dance");
		verify(todoServiceMock, atLeast(1)).deleteTodos("Learn to Dance");
		then(todoServiceMock).should().deleteTodos("Learn to Dance");

		verify(todoServiceMock, never()).deleteTodos("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodos("Learn Spring MVC");

		verify(todoServiceMock, never()).deleteTodos("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodos("Learn Spring");
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// Then (similarities)
		then(todoServiceMock).should().deleteTodos(stringArgumentCaptor.capture());

		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {
		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

		List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		// Then (similarities)
		then(todoServiceMock).should(times(2)).deleteTodos(stringArgumentCaptor.capture());

		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}

}
