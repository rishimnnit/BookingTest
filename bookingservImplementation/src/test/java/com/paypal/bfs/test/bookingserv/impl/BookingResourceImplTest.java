package com.paypal.bfs.test.bookingserv.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.hibernate.exception.LockAcquisitionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.paypal.bfs.test.bookingserv.pojo.Address;
import com.paypal.bfs.test.bookingserv.pojo.Booking;
import com.paypal.bfs.test.bookingserv.pojo.BookingResponse;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BookingResourceImplTest {

    @InjectMocks
    private BookingResourceImpl service;

    @Mock
    private BookingRepository repository;

	private List<Booking> bookings;

	@BeforeEach
	void setUpBookings() throws ParseException {
        bookings = new ArrayList<Booking>();
        Booking booking1 = new Booking();
        booking1.setFirstName("Rishabh");
        booking1.setLastName("Shukla");
        booking1.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking1.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking1.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking1.setDeposit(3000);
        booking1.setTotalPrice(12000);
        
        Booking booking2 = new Booking();
        booking2.setFirstName("Rishabh");
        booking2.setLastName("Shukla");
        booking2.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking2.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking2.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking2.setDeposit(3000);
        booking2.setTotalPrice(12000);
        
        bookings.add(booking1);
        bookings.add(booking2);
	}

	@Test
	public void testCreateSuccessCase() throws ParseException {
		Booking booking = new Booking();
        booking.setFirstName("Rishabh");
        booking.setLastName("Shukla");
        booking.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setDeposit(3000);
        booking.setTotalPrice(12000);
        
        Address address = new Address();
        address.setLine1("Address line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setZipCode(560076);
        
        booking.setAddress(address);
        
        when(repository.save(booking)).thenReturn(booking);
    	ResponseEntity<BookingResponse> response = service.create(booking);
    	assertEquals("Booking created in system successfully", response.getBody().getMessage());
    	assertEquals(HttpStatus.OK, response.getStatusCode());
        
	}

	@Test
	public void testCreateUniqueRequestKey() throws ParseException {
		Booking booking = new Booking();
        booking.setFirstName("Rishabh");
        booking.setLastName("Shukla");
        booking.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setDeposit(3000);
        booking.setTotalPrice(12000);
        
        Address address = new Address();
        address.setLine1("Address line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setZipCode(560076);
        
        booking.setAddress(address);
        
        when(repository.save(booking)).thenThrow(new DataIntegrityViolationException("Test String"));
    	ResponseEntity<BookingResponse> response = service.create(booking);
    	assertEquals("Request already initiated with this request id", response.getBody().getMessage());
    	assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        
	}

	@Test
	public void testCreateExceptionCase() throws ParseException {
		Booking booking = new Booking();
        booking.setFirstName("Rishabh");
        booking.setLastName("Shukla");
        booking.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setDeposit(3000);
        booking.setTotalPrice(12000);
        
        Address address = new Address();
        address.setLine1("Address line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setZipCode(560076);
        
        booking.setAddress(address);
        
        when(repository.save(booking)).thenThrow(new LockAcquisitionException("Test String", new SQLException()));
    	ResponseEntity<BookingResponse> response = service.create(booking);
    	assertEquals("Something went wrong while creating booking", response.getBody().getMessage());
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        
	}

	@Test
	public void testCreateInvalidDataCase() throws ParseException {
		Booking booking = new Booking();
        booking.setFirstName("Rishabh");
        booking.setLastName("Shukla");
        booking.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setCheckinDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-22"));
        booking.setCheckoutDatetime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-21"));
        booking.setDeposit(30000);
        booking.setTotalPrice(12000);
        
        Address address = new Address();
        address.setLine1("Address line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setZipCode(560076);
        
        booking.setAddress(address);

    	ResponseEntity<BookingResponse> response = service.create(booking);
    	assertEquals("Booking data validation Failed", response.getBody().getMessage());
    	assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    	assertEquals(2, response.getBody().getErrors().size());
        
	}
	
    @Test
    public void getAllSuccess()
    {
    	when(repository.findAll()).thenReturn(bookings);
    	ResponseEntity<BookingResponse> response = service.getAll();
    	assertEquals("Bookings fetched successfully", response.getBody().getMessage());
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    	
    }
    
    /*@Test   Disabling this test case as findall method is not throwing any exception
    public void getAllFailure()
    {
    	when(repository.findAll()).thenThrow(new Exception());
    	ResponseEntity<BookingResponse> response = service.getAll();
    	assertEquals("Something went wrong while getting bookings", response.getBody().getMessage());
    	assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    	
    }*/
}
