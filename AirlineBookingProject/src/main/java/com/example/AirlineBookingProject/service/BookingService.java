package com.example.AirlineBookingProject.service;

import com.example.AirlineBookingProject.model.Booking;
import com.example.AirlineBookingProject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AirlineBookingProject.model.User;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

}
