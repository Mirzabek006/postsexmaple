package com.example.demo.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//null qiymatlari maydoni kurinmaydi
public class BaseResponse <T>{
    private Boolean success;
    private String message;
    private T data;
    private Integer code;//M-n:200,400,404,500
    @Builder.Default
    private LocalDateTime timestamp=LocalDateTime.now();
    //Muvaffaqiyatli javoblar uchun static yordamchi metodlar
    public static <T> BaseResponse<T> success(T data){
        return BaseResponse.<T>builder()
                .success(true)
                .message("Operation completed successfully")
                .data(data)
                .code(200)
                .build();
    }
    public static <T> BaseResponse<T> success(String message,T data){
        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .data(null)
                .code(200)
                .build();
    }
    ///Xatoliklar uchun static yordamchi metod
    public static <T>BaseResponse<T>error(String message,Integer code){
        return BaseResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .code(code)
                .build();
    }
}
