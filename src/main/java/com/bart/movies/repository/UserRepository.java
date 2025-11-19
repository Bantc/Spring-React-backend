package com.bart.movies.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bart.movies.data.User;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByUsername(String username);
    public boolean existsByUsername(String username);
}
