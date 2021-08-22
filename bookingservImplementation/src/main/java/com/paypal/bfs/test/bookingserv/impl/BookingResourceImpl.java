package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.pojo.Booking;
import com.paypal.bfs.test.bookingserv.pojo.BookingResponse;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	private BookingRepository repository;
	
    @Override
    public ResponseEntity<BookingResponse> create(@Valid Booking booking) {
        BookingResponse response = new BookingResponse();
        if(!validate(booking, response)) {
        	return new ResponseEntity<BookingResponse>(response, HttpStatus.BAD_REQUEST);
        }

        try {
        	repository.save(booking);
        } catch(DataIntegrityViolationException ex) {
            response.setMessage("Request already initiated with this request id");
            return new ResponseEntity<BookingResponse>(response, HttpStatus.CONFLICT);
        }  catch(Exception ex) {
        	response.setMessage("Something went wrong while creating booking");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMessage("Booking created in system successfully");
        return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingResponse> getAll() {
        BookingResponse response = new BookingResponse();
    	try {
    		List<Booking> bookings = repository.findAll();
            response.setMessage("Bookings fetched successfully");
            response.setBookings(bookings);
    	} catch(Exception ex) {
        	response.setMessage("Something went wrong while getting bookings");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	}

    	return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
    }

    private boolean validate(Booking booking, BookingResponse response) {
    	
    	List<String> errors = new ArrayList<String>();
    	
    	if(booking.getCheckinDatetime().after(booking.getCheckoutDatetime())) {
    		errors.add("Booking checkout date should be after booking checkin date");
    	}

    	if(booking.getDeposit() > booking.getTotalPrice()) {
    		errors.add("Booking deposit can't be greater than total price");
    	}

    	if(errors.size() > 0) {
    		response.setMessage("Booking data validation Failed");
    		response.setErrors(errors);
    		return false;
    	}
    	return true;
    }

}
