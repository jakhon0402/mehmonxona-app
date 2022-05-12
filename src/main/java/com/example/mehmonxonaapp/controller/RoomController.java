package com.example.mehmonxonaapp.controller;

import com.example.mehmonxonaapp.dto.RoomDto;
import com.example.mehmonxonaapp.entity.Hotel;
import com.example.mehmonxonaapp.entity.Room;
import com.example.mehmonxonaapp.repository.HotelRepo;
import com.example.mehmonxonaapp.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepo roomRepo;

    @Autowired
    HotelRepo hotelRepo;

    @GetMapping("/getByHotelId/{id}")
    public Page<Room> getRoomsById(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<Room> roomPage = roomRepo.findAllByHotelId(id, pageable);
        return roomPage;
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get();
        }
        else return new Room();
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepo.findById(roomDto.getHotelId());
        if(optionalHotel.isPresent()){
            Room room = new Room();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            room.setHotel(optionalHotel.get());
            roomRepo.save(room);
            return "Room succesfully edited !";
        }
        else return "Bunday idlik hotel mavjud emas!";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if(optionalRoom.isPresent()){
            roomRepo.delete(optionalRoom.get());
            return "Room successfully deleted !";
        }
        else return "Bunday idlik room mavjud emas !";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepo.findById(id);
        if(optionalRoom.isPresent()){
            Optional<Hotel> optionalHotel = hotelRepo.findById(roomDto.getHotelId());
            if(optionalHotel.isPresent()){
                Room room = optionalRoom.get();
                room.setNumber(roomDto.getNumber());
                room.setFloor(roomDto.getFloor());
                room.setHotel(optionalHotel.get());
                return "Room successfully edited !";
            }
            else return "Bunday idlik Hotel mavjud emas !";
        }
        else return "Bunday idlik room mavjud emas !";
    }
}
