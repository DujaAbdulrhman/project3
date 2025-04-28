package com.example.project3.API;

public class ApiException extends RuntimeException{
    public ApiException(String massage){
        super(massage);
    }
}
