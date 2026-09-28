package com.example.demo.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {
    //Resurs topilmaganda(404)
    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleResourceNotFound(ConfigDataResourceNotFoundException ex){
        BaseResponse<Void> response=BaseResponse.error(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    //Yomon so'rov yuborilganda /Biznes xatoliklar (400) uchun
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<Void>> handleBadRequest(BadRequestException ex){
        BaseResponse<Void> response=BaseResponse.error(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    //dto validation xatoliklari uchun (Validatsiydan o'tmay qolganda:@Valid,@NotNUll va h.k)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String,String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        BaseResponse<Map<String,String>>response=BaseResponse.<Map<String, String>>builder()
                .success(false)
                .message("Validation filed")
                .data(errors)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    //Barcha ko'zda tutilmagan kutilmagan xatolar uchun (500 Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>>handleGlobalException(Exception ex){

        log.error("Unexpected error:",ex);
        BaseResponse<Void>response=BaseResponse.error("Ichki server xatoligi yuz beradi",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
