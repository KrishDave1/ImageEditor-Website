package com.iiitb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.iiitb.imageEffectApplication.model.LogModel;

public interface LogRepositry extends MongoRepository<LogModel, String> {
    
}

