package com.vendingmachine.vendingmachineapplication.controller.advice;

import com.vendingmachine.vendingmachineapplication.exception.InSufficientAmountException;
import com.vendingmachine.vendingmachineapplication.exception.InvalidCoinTypeException;
import com.vendingmachine.vendingmachineapplication.exception.NoChangeException;
import com.vendingmachine.vendingmachineapplication.exception.NoItemFoundInSlotException;
import com.vendingmachine.vendingmachineapplication.exception.ProductSlotNotFoundException;
import com.vendingmachine.vendingmachineapplication.model.CoinType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
@RequestMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ExceptionMapper {

    @ExceptionHandler(ProductSlotNotFoundException.class)
    public ResponseEntity<Object> handleProductSlotNotFoundException(
            ProductSlotNotFoundException ex) {
        return errorDetailsResponseEntity(ex, NOT_FOUND, "The given product slot is not found");
    }

    @ExceptionHandler(InSufficientAmountException.class)
    public ResponseEntity<Object> handleInsufficientAmountException(
            InSufficientAmountException ex) {
        return errorDetailsResponseEntity(ex, BAD_REQUEST, "The amount is insufficient");
    }

    @ExceptionHandler(NoChangeException.class)
    public ResponseEntity<Object> handleNoChangeException(
            NoChangeException ex) {
        return errorDetailsResponseEntity(ex, NOT_FOUND, "No change available");
    }

    @ExceptionHandler(NoItemFoundInSlotException.class)
    public ResponseEntity<Object> handleNoItemFoundInSlotException(
            NoItemFoundInSlotException ex) {
        return errorDetailsResponseEntity(ex, NOT_FOUND, "No Item found in slot");
    }

    @ExceptionHandler(InvalidCoinTypeException.class)
    public ResponseEntity<Object> handleInvalidCoinTypeException(
            InvalidCoinTypeException ex) {
        return errorDetailsResponseEntity(ex, NOT_FOUND, "The given coin type is not found");
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        return errorDetailsResponseEntity(ex, BAD_REQUEST, ex.getLocalizedMessage());
    }

    public ResponseEntity<Object> errorDetailsResponseEntity(Exception ex, HttpStatus httpStatus, String errorMsg) {

        ErrorResponse errorDetails = new ErrorResponse(httpStatus.value(),httpStatus.getReasonPhrase(),errorMsg);

        return new ResponseEntity<>(errorDetails, httpStatus);
    }
}
