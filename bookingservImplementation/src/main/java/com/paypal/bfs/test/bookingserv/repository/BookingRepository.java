package com.paypal.bfs.test.bookingserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.bookingserv.pojo.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
