package com.example.demo.error;

import org.springframework.data.crossstore.ChangeSetPersister;

public class DataNotFoundException extends ChangeSetPersister.NotFoundException {
    public DataNotFoundException(String s) {

    }
}
