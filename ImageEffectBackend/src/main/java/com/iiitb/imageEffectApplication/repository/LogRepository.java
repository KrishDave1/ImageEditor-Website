package com.iiitb.imageEffectApplication.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.iiitb.imageEffectApplication.model.LogModel;

public interface LogRepository extends MongoRepository<LogModel, String> {
    
}