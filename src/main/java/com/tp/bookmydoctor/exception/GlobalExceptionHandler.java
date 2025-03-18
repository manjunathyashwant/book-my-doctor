package com.tp.bookmydoctor.exception;

import com.tp.bookmydoctor.responsedto.GlobalResponseDto;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


// to handle the UI side exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResponseDto validationFailed(MethodArgumentNotValidException e){

        List<String> strings=new ArrayList<>();
        for (FieldError error:e.getBindingResult().getFieldErrors()){
            strings.add(error.getField()+" "+error.getDefaultMessage());
        }
        String messages = String.join(",", strings);
        return new GlobalResponseDto(true,messages,null);
    }

    //to handle user not found exception
    @ExceptionHandler( DataFoundException.class)
    public GlobalResponseDto userException( DataFoundException e) {
        return new GlobalResponseDto(true, e.getMessage(), null);
    }
    
    
    @ExceptionHandler(InvalidCredentials.class)
    public GlobalResponseDto invalidCredentials(InvalidCredentials credentials) {
    	return new GlobalResponseDto(true,credentials.getMessage(),null);
    }
}
