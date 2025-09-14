package com.bart.movies.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bart.movies.data.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
