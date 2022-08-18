package com.blz.employeepayroll.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class EmployeeNotFoundException extends RuntimeException{
    private int statuscode;
    private String message;

    public EmployeeNotFoundException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
        this.message = message;
    }
}
