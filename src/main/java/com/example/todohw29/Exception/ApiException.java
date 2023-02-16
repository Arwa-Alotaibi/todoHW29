package com.example.todohw29.Exception;

public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);
    }
}