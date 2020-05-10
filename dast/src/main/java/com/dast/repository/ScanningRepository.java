package com.dast.repository;

import com.dast.model.Scanning;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScanningRepository extends MongoRepository<Scanning,String> {

    Scanning findByUrl(String url);
}
