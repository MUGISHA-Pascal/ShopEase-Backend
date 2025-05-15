package com.starter.mugisha.exceptions;

public class ApiRequestException extends RuntimeException{
    public  ApiRequestException(String message){
        super(message);
    }
}
