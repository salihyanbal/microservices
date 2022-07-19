package com.microservices.mail.service;

import com.microservices.mail.dto.ChangeEmailRequest;
import com.microservices.mail.dto.CreateEmailRequest;
import com.microservices.mail.dto.EmailDto;
import com.microservices.mail.dto.UpdateEmailRequest;
import com.microservices.mail.model.Email;

public interface EmailService {

    EmailDto createEmail(CreateEmailRequest emailRequest);
    EmailDto updateEmail(String id, UpdateEmailRequest emailRequest);
    Boolean changeEmail(String id, ChangeEmailRequest emailRequest);

    Boolean deleteEmail(String id);
    Email getById(String id);
    Email getByEmail(String email);
}
