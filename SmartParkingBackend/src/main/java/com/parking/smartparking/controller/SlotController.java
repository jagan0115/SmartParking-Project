package com.parking.smartparking.controller;

import com.parking.smartparking.model.ParkingSlot;
import com.parking.smartparking.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
@CrossOrigin
public class SlotController {

    @Autowired
    private ParkingSlotRepository repo;

    // Add Slot
    @PostMapping("/add")
    public ParkingSlot addSlot(@RequestBody ParkingSlot slot) {
        slot.setStatus("AVAILABLE");
        return repo.save(slot);
    }

    // View Slots
    @GetMapping("/all")
    public List<ParkingSlot> getAllSlots() {
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSlot(@PathVariable Long id) {
        repo.deleteById(id);
    }
}