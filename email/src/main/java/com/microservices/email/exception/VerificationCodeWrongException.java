package com.microservices.email.exception;

public class VerificationCodeWrongException extends RuntimeException{

    public VerificationCodeWrongException(){
        super("Verification code wrong!");
    }

    public VerificationCodeWrongException(String message){
        super(message);
    }

}
