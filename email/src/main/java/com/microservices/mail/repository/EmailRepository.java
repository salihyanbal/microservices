package com.microservices.mail.repository;

import com.microservices.mail.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailRepository extends MongoRepository<Email, String> {

    Optional<Email> findByEmail(String email);
}
