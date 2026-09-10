package com.parking.smartparking.repository;

import com.parking.smartparking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStatus(String status);

    List<Booking> findByStatusNot(String status);

}