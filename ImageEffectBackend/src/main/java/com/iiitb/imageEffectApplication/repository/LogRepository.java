package com.iiitb.imageEffectApplication.repository;

import com.iiitb.imageEffectApplication.model.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LogRepository extends MongoRepository<LogModel, String> {
    @Query(value = "{effectName:'?0'}", fields = "{'timestamp' : 1, 'filename' : 1, 'effectName' : 1, 'optionValues' : 1}")
    List<LogModel> findLogByEffectName(String effect);
}