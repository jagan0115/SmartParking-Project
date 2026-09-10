package com.parking.smartparking.controller;

import com.parking.smartparking.model.Booking;
import com.parking.smartparking.model.ParkingSlot;
import com.parking.smartparking.repository.BookingRepository;
import com.parking.smartparking.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private ParkingSlotRepository slotRepo;

    @PostMapping("/add")
    public Booking bookSlot(@RequestBody Booking booking) {

        // Save booking
        booking.setStatus("ACTIVE");
        Booking saved = bookingRepo.save(booking);

        // Update slot status
        ParkingSlot slot = slotRepo.findBySlotNumber(booking.getSlotNumber());

        if (slot != null) {
            slot.setStatus("BOOKED");
            slotRepo.save(slot);
        }

        return saved;
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @GetMapping("/active")
    public List<Booking> getActiveBookings() {
        return bookingRepo.findByStatus("ACTIVE");
    }

    @GetMapping("/history")
    public List<Booking> getHistory() {
        return bookingRepo.findByStatusNot("ACTIVE");
    }

    @PutMapping("/complete/{id}")
    public Booking completeBooking(@PathVariable Long id) {

        Booking booking = bookingRepo.findById(id).orElse(null);

        if (booking != null) {
            booking.setStatus("COMPLETED");
            bookingRepo.save(booking);

            // free slot
            ParkingSlot slot = slotRepo.findBySlotNumber(booking.getSlotNumber());
            if (slot != null) {
                slot.setStatus("AVAILABLE");
                slotRepo.save(slot);
            }
        }

        return booking;
    }

}
