package com.tms.toc.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.toc.api.beans.TOC;
import com.tms.toc.api.repo.TOCRepository;

/**
 * 
 * @author Sangita Halder
 * @see contains service methods of TOC.
 * @since feb 2022
 *
 */

@Service
public class TOCService {
	
	@Autowired
	private TOCRepository tocRepo;
	
	/**
	 * 
	 * Save the TOC into the database and then return the TOC.
	 * @param toc
	 * @return saved TOC entity
	 */
	public TOC saveTOC(TOC toc) {
		return tocRepo.insert(toc);
	}
	
	/**
	 *
	 * Fetch all the TOC data from the database and return the list of TOC object.
	 * @return List of TOC objects
	 */
	public List<TOC> getAllTocs(){
		return tocRepo.findAll();
	}
	
	/**
	 * 
	 * Retrieves the TOC of mentioned id.
	 * @param id
	 * @return the TOC of mentioned id or Optional#empty() if not found
	 */
	public Optional<TOC> getTocById(Integer id){
		return tocRepo.findById(id);
	}
	
	/**
	 * Returns the particular TOC if found else returns null
	 * @param id
	 * @return TOC if found, null if not found
	 */
	public TOC getTocByIdAsObject(Integer id){
		Optional<TOC> byId = tocRepo.findById(id);
		return byId.isPresent()?byId.get(): null;
	}
}
