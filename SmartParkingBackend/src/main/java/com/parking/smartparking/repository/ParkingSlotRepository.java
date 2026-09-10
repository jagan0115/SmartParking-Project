package com.parking.smartparking.repository;

import com.parking.smartparking.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    ParkingSlot findBySlotNumber(String slotNumber);
}