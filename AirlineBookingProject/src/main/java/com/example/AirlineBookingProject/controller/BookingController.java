package com.example.AirlineBookingProject.controller;

import com.example.AirlineBookingProject.model.Booking;
import com.example.AirlineBookingProject.model.Flight;
import com.example.AirlineBookingProject.model.User;
import com.example.AirlineBookingProject.service.BookingService;
import com.example.AirlineBookingProject.service.FlightService;
import com.example.AirlineBookingProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingService.getBookingsByUser(user);
        model.addAttribute("bookings", bookings);
        return "booking-list";
    }

    @PostMapping("/book")
    public String bookFlight(@RequestParam Long flightId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Flight flight = flightService.getFlightById(flightId);

        if (flight == null) {
            model.addAttribute("error", "Flight not found.");
            return "flight-search-results";
        }

        if (flight.getAvailableSeats() <= 0) {
            model.addAttribute("error", "No available seats for this flight.");
            return "flight-search-results";
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDate.now());

        bookingService.saveBooking(booking);

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightService.saveFlight(flight);

        session.setAttribute("message", "예약이 완료되었습니다!");
        return "redirect:/";
    }

}
