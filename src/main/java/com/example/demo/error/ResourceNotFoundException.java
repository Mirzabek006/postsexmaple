package com.example.demo.error;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id){
        super ("Resurs topilmadi, id:"+id);
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
