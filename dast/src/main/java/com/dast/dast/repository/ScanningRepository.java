package com.dast.dast.repository;

import com.dast.dast.model.Scanning;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScanningRepository extends MongoRepository<Scanning,String> {


}
