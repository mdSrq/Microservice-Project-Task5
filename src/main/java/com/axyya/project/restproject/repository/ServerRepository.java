package com.axyya.project.restproject.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.axyya.project.restproject.model.Server;

@EnableMongoRepositories
public interface ServerRepository extends MongoRepository<Server, String> {
	List<Server> findByNameContaining(String name);
}
