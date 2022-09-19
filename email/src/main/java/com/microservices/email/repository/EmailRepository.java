package com.microservices.email.repository;

import com.microservices.email.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailRepository extends MongoRepository<Email, String> {

    Optional<Email> findByEmailAddress(String email);
}
