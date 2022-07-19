package com.microservices.email.service.Implementation;

import com.microservices.email.dto.*;
import com.microservices.email.exception.EmailNotFoundException;
import com.microservices.email.exception.VerificationCodeWrongException;
import com.microservices.email.model.Email;
import com.microservices.email.repository.EmailRepository;
import com.microservices.email.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmailDto createEmail(CreateEmailRequest emailRequest) {
        Email email = this.modelMapper.map(emailRequest, Email.class);
        this.emailRepository.save(email);
        return this.modelMapper.map(email.getId(), EmailDto.class);
    }

    @Override
    public EmailDto updateEmail(String id, UpdateEmailRequest emailRequest) {
        Email email = this.getById(id);
        Email emailToUpdate = new Email(id, emailRequest.getEmailAddress(), emailRequest.getCode(), emailRequest.getConfirmed());
        return this.modelMapper.map(this.emailRepository.save(emailToUpdate), EmailDto.class);
    }

    @Override
    public Boolean changeEmail(String id, ChangeEmailRequest emailRequest) {
        Email email = this.getById(id);
        if(email.getEmailAddress().equals(emailRequest.getEmailAddress())){
            return false; // or throw email not changed exception??? and change return type to emaildto
        }
        UpdateEmailRequest emailToChange = new UpdateEmailRequest(emailRequest.getEmailAddress(), createRandomCode(), false);
        this.updateEmail(id, emailToChange);
        sendMail(id);
        return true;
    }

    @Override
    public EmailDto verifyEmail(String id, VerifyEmailRequest verifyRequest) {
        Email email = this.getById(id);
        if(email.getCode() != verifyRequest.getCode()){
            throw new VerificationCodeWrongException();
        }
        UpdateEmailRequest emailToUpdate = new UpdateEmailRequest(email.getEmailAddress(), email.getCode(), true);
        return this.updateEmail(id, emailToUpdate);
    }

    @Override
    public Boolean deleteEmail(String id) {
        this.emailRepository.deleteById(id);
        return true;
    }

    @Override
    public Email getById(String id) {
        return this.emailRepository.findById(id).orElseThrow(() -> new EmailNotFoundException());
    }

    @Override
    public Email getByEmail(String email) {
        return this.emailRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException());
    }

    public int createRandomCode(){
        Random rand = new Random();
        int code = rand.nextInt(9999) + 10000;
        return code;
    }

    private Boolean sendMail(String id){
        System.out.println("Email sender service is not created");
        System.out.println("Rabbitmq queue published");
        return true;
    }
}
