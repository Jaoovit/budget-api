package com.oliveira.budget.service;

import org.springframework.stereotype.Service;
import com.oliveira.budget.domain.error.Error;

@Service
public class ErrorService {

    public Error getError(String code, String message) {
        Error error = new Error();

        error.setCode(code);
        error.setMessage(message);

        return error;
    }
}
