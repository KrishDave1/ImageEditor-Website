package com.iiitb.imageEffectApplication.repository;
import com.iiitb.imageEffectApplication.model.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<LogModel, String> {
    @Query(value = "{effectName : '?0'}", fields = "{'timestamp' :  1, 'filename' :  1, 'effectName' :  1, 'optionValues' : 1}")
    List<LogModel> findLogByEffectName(String effect);
}

// This code declares a Spring Data MongoDB repository interface (LogRepository) for the LogModel entity.
// It includes a custom query method (findLogByEffectName) with a MongoDB query that searches for logs based on
// the 'effectName' field and specifies the fields to be included in the result.
