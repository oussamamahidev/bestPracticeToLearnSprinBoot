package com.pokemonreview.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> handleError(PokemonNotFoundException ex, WebRequest request){

        ErrorObject error = new ErrorObject();

        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(error.getMessage());
        error.setTimestamp(new Date());


        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleErrorReview(ReviewNotFoundException ex, WebRequest request){

        ErrorObject error = new ErrorObject();

        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(error.getMessage());
        error.setTimestamp(new Date());


        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
}
