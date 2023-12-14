package com.iiitb.imageEffectApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoInterface extends MongoRepository <ImageEffectApplication, String> {
    @Query("{name:'?0'}")
}