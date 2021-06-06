package com.example.postad.exceptionhandle;


public class BaseException extends RuntimeException {


    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message,Throwable cause) {
        super(message, cause);
    }


}


