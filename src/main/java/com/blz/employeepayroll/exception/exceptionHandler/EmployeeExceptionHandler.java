package com.blz.employeepayroll.exception.exceptionHandler;

import com.blz.employeepayroll.exception.EmployeeNotFoundException;
import com.blz.employeepayroll.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class EmployeeExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseUtil> response(EmployeeNotFoundException exception){
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setErrorcode(400);
        responseUtil.setMessage(exception.getMessage());
        return new ResponseEntity<>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseUtil> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setErrorcode(500);
        responseUtil.setMessage(e.getMessage());
        return new ResponseEntity<ResponseUtil>(responseUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
