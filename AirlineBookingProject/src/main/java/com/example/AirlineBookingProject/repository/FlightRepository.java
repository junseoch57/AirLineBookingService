package com.example.AirlineBookingProject.repository;

import com.example.AirlineBookingProject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.departureDate = :departureDate AND f.returnDate = :returnDate AND f.availableSeats >= :passengers AND f.destination = :destination")
    List<Flight> findFlights(@Param("departureDate") LocalDate departureDate,
                             @Param("returnDate") LocalDate returnDate,
                             @Param("passengers") int passengers,
                             @Param("destination") String destination);
}
