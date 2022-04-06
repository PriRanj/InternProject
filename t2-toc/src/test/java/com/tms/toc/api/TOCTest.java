package com.tms.toc.api;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tms.toc.api.beans.TOC;
import com.tms.toc.api.controller.TOCController;
import com.tms.toc.api.exception.TOCNotFoundException;
import com.tms.toc.api.repo.TOCRepository;
import com.tms.toc.api.service.TOCService;

/**
 * 
 * Test cases for toc controller methods
 * @author Sangita Halder
 * @since Feb 2022
 * 
 *
 */
@SpringBootTest
public class TOCTest {

	@Autowired
	private TOCService service;

	@Autowired
	private TOCController controller;

	@MockBean
	private TOCRepository repo;

	@BeforeEach
	public void setUp() {
		Optional<TOC> toc = Optional
				.of(new TOC(101, "java", 14, "master in java", "basic knowledge of java", "freshers", 100));
		List<TOC> list = Arrays
				.asList(new TOC(101, "java", 14, "master in java", "basic knowledge of java", "freshers", 100));
		Optional<TOC> emptyToc = Optional.empty();
		// mocked object
		when(repo.findById(101)).thenReturn(toc);
		when(repo.findById(102)).thenReturn(emptyToc);
		when(repo.findAll()).thenReturn(list);
		when(repo.insert(toc.get())).thenReturn(toc.get());

	}

	@Test
	@DisplayName("When toc id is given return valid object")
	public void whenTocIdIsGivenThenReturnObjectIfExists() throws TOCNotFoundException {

		// checking the service method
		assertEquals(101, service.getTocById(101).get().getId());

		// checking the controller method
		assertEquals(101, controller.getTocById(101).getId());

	}

	@Test
	@DisplayName("when toc id is given return valid toc object")
	public void whenTocIdIsGivenThenReturnTocObjectIfExist() {
		if (repo.findById(101).isPresent()) {
			assertEquals(101, service.getTocByIdAsObject(101).getId());
		}
	}

	@Test
	@DisplayName("when toc id is given return null if not found")
	public void whenTocIdIsGivenThenReturnNullIfNotExist() throws TOCNotFoundException {

		// checking the controller method
		assertThrows(TOCNotFoundException.class, () -> controller.getTocById(102));

	}

	@Test
	@DisplayName("get all the tocs and list should give you an array of 1 toc")
	public void getAllTocs() {

		// checking the controller method
		assertEquals(1, controller.getTocs().size());
	}

	@Test
	@DisplayName("save the valid toc and acknowledge the same")
	public void saveTocToTheServer() {
		TOC toc = new TOC(101, "java", 14, "master in java", "basic knowledge of java", "freshers", 100);

		// checking the controller method
		assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(toc), controller.saveTOC(toc));

	}

	@Test
	@DisplayName("check connection")
	public void checkConnection() {
		assertEquals("Service Is Up", controller.sayHello());
	}

}
