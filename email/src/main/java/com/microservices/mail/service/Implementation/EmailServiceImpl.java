package com.microservices.mail.service.Implementation;

import com.microservices.mail.dto.ChangeEmailRequest;
import com.microservices.mail.dto.CreateEmailRequest;
import com.microservices.mail.dto.EmailDto;
import com.microservices.mail.dto.UpdateEmailRequest;
import com.microservices.mail.exception.EmailNotFoundException;
import com.microservices.mail.model.Email;
import com.microservices.mail.repository.EmailRepository;
import com.microservices.mail.service.EmailService;
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
