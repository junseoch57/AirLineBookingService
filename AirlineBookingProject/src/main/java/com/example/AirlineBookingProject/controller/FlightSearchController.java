package com.example.AirlineBookingProject.controller;

import com.example.AirlineBookingProject.model.Flight;
import com.example.AirlineBookingProject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/flight-search")
public class FlightSearchController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String showFlightSearchForm() {
        return "flight-search";
    }

    @PostMapping
    public String searchFlights(@RequestParam String departureDate, @RequestParam String returnDate,
                                @RequestParam int passengers, @RequestParam String destination, Model model) {
        LocalDate depDate = LocalDate.parse(departureDate);
        LocalDate retDate = LocalDate.parse(returnDate);
        List<Flight> flights = flightService.searchFlights(depDate, retDate, passengers, destination);
        model.addAttribute("flights", flights);
        return "flight-search-results";
    }

}
