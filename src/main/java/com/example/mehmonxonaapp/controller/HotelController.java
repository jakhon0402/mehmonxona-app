package com.example.mehmonxonaapp.controller;

import com.example.mehmonxonaapp.entity.Hotel;
import com.example.mehmonxonaapp.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepo hotelRepo;

    @GetMapping
    public List<Hotel> getAllHotels(){
        return hotelRepo.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if(optionalHotel.isPresent()){
            return optionalHotel.get();
        }
        else return new Hotel();
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        hotelRepo.save(hotel);
        return "Hotel added!";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if(optionalHotel.isPresent()){
            hotelRepo.delete(optionalHotel.get());
            return "Successfully deleted!";
        }
        else return "Bunday idlik hotel mavjud emas!";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if(optionalHotel.isPresent()){
            Hotel hoteledited = optionalHotel.get();
            hoteledited.setName(hotel.getName());
            hotelRepo.save(hoteledited);
            return "Successfully edited!";
        }
        else return "Bunday idlik hotel mavjud emas!";
    }

}
