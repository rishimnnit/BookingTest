package com.paypal.bfs.test.bookingserv.eception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paypal.bfs.test.bookingserv.pojo.BookingResponse;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       List<String> details = new ArrayList<>();
       for(ObjectError error : ex.getBindingResult().getAllErrors()) {
           details.add(error.getDefaultMessage());
       }
       BookingResponse error = new BookingResponse("Booking data validation Failed", details);
       return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
   }

}
