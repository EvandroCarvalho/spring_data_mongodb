package com.spring_mongodb.repository;

import com.spring_mongodb.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    public Task findByName(String name);
}
