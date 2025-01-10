package com.example.AirlineBookingProject.service;

import com.example.AirlineBookingProject.model.Flight;
import com.example.AirlineBookingProject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(LocalDate departureDate, LocalDate returnDate, int passengers, String destination) {
        return flightRepository.findFlights(departureDate, returnDate, passengers, destination);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
