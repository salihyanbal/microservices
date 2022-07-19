package com.microservices.email.service;

import com.microservices.email.dto.*;
import com.microservices.email.model.Email;

public interface EmailService {

    EmailDto createEmail(CreateEmailRequest emailRequest);
    EmailDto updateEmail(String id, UpdateEmailRequest emailRequest);
    Boolean changeEmail(String id, ChangeEmailRequest emailRequest);
    EmailDto verifyEmail(String id, VerifyEmailRequest verifyRequest);

    Boolean deleteEmail(String id);
    Email getById(String id);
    Email getByEmail(String email);
}
