package com.bala.repository;

import com.bala.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query("{'username': ?0}")
    Optional<User> findByUsername(String username);
}