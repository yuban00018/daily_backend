package com.daily.controller;

import com.daily.exception.AllException;
import com.daily.exception.EmAllException;
import com.daily.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@CrossOrigin
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handler(Exception e){
        Result result = new Result();
        log.warn(e.getMessage());
        if (e instanceof AllException){
            AllException exception = (AllException)e;
            result.setCode(exception.getErrCode());
            result.setMessage(exception.getMessage());
        } else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult res = exception.getBindingResult();
            final List<FieldError> fieldErrors = res.getFieldErrors();
            StringBuilder builder = new StringBuilder();
            for (FieldError error : fieldErrors) {
                builder.append(error.getDefaultMessage()).append("\n");
            }
            result.setMessage(builder.toString());
            result.setCode(EmAllException.BAD_REQUEST.getErrCode());
        } else if(e instanceof HttpMessageNotReadableException){
            HttpMessageNotReadableException exception = (HttpMessageNotReadableException) e;
            if (Objects.requireNonNull(exception.getMessage()).contains("java.time.LocalDateTime")) {
                result.setMessage("时间格式有误");
            } else {
                result.setMessage(exception.getMessage());
            }
            result.setCode(EmAllException.BAD_REQUEST.getErrCode());
        } else {
            result.setCode(EmAllException.UNKNOWN_ERROR.getErrCode());
            result.setMessage(EmAllException.UNKNOWN_ERROR.getMsg());
        }
        return result;
    }
}
