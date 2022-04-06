package com.tms.toc.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.toc.api.beans.TOC;
import com.tms.toc.api.exception.TOCNotFoundException;
import com.tms.toc.api.service.TOCService;

/**
 * 
 * @author Sangita Halder
 * @see 
 * @since Feb 2022
 *
 */

@RestController
public class TOCController {
	
	@Autowired
	private TOCService tocService;
	
	
	@GetMapping("/health")
	public String sayHello() {
		return "Service Is Up";
	}
	
	@PostMapping("/toc")
	public ResponseEntity<?> saveTOC(@RequestBody TOC toc) {
		TOC returnedTOC =  tocService.saveTOC(toc);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnedTOC);
	} 
	
	@GetMapping("/toc")
	public List<TOC> getTocs(){
		return tocService.getAllTocs();
	}
	
	@GetMapping("/toc/{id}")
	public TOC getTocById(@PathVariable("id") Integer id) throws TOCNotFoundException{
		TOC returnedToc = tocService.getTocByIdAsObject(id);
		if(returnedToc == null) {
			throw new TOCNotFoundException("TOC with id " + id + " not found!!");
		}
		return returnedToc;
	}
	
	
	
}
