package com.example.AirlineBookingProject.repository;

import com.example.AirlineBookingProject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AirlineBookingProject.model.User;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
