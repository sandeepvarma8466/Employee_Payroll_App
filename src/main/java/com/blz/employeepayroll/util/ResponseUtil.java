package com.blz.employeepayroll.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUtil {
    private int errorcode;
    private String message;
    private Object token;

}

