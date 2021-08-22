package com.paypal.bfs.test.bookingserv.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

	private String message;

	private List<String> errors;

	private List<Booking> bookings;

	public BookingResponse(String message, List<String> errors) {
		super();
		this.message = message;
		this.errors = errors;
	}

	public BookingResponse() {
		super();
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
