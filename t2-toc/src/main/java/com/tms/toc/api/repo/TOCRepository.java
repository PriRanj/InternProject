package com.tms.toc.api.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tms.toc.api.beans.TOC;

@Repository
public interface TOCRepository extends MongoRepository<TOC, Integer>{

}
