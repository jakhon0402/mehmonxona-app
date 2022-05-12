package com.example.mehmonxonaapp.repository;

import com.example.mehmonxonaapp.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room,Integer> {
    Page<Room> findAllByHotelId(Integer hotelId, Pageable pageable);
}
