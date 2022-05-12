package com.example.mehmonxonaapp.repository;

import com.example.mehmonxonaapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,Integer> {
}
