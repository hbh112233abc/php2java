package com.example.demo.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.Result;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotPermissionException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotPermissionException.class)
    public Result notPermissionException(NotPermissionException e){
        SaHolder.getResponse().setStatus(403);
        return Result.error(403,e.getMessage());
    }
}
