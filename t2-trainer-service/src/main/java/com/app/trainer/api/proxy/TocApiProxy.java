package com.app.trainer.api.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.trainer.api.beans.TOC;

@FeignClient(name = "TOC-SERVICE")
public interface TocApiProxy {
	
	@PostMapping("/toc")
	public ResponseEntity<?> saveToc(@RequestBody TOC toc);
	
	@GetMapping("/toc")
	public List<TOC> getTocs();
	

}
