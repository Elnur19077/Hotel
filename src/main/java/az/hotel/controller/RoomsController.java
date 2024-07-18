package az.hotel.controller;

import az.hotel.dto.response.Response;
import az.hotel.dto.response.RoomsResp;
import az.hotel.service.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomsController {
    private final RoomsService roomsService;

    @GetMapping("/list")
    public Response<List<RoomsResp>> getAllRooms() {
        return roomsService.getAllRooms();
    }

    @GetMapping("/list/{roomsType}")
    public Response<List<RoomsResp>> getRoomsByType(@PathVariable String roomsType) {
        return roomsService.getRoomsByType(roomsType);
    }

    @GetMapping("/list/byId/{id}")
    public Response<RoomsResp> getRoomsById(@PathVariable Long id) {
        return roomsService.getRoomsById(id);
    }
    @GetMapping("/list/byPrice/{price}")
    public Response<List<RoomsResp>> getRoomsByPrice(@PathVariable Integer price) {
        return  roomsService.getRoomsByPrice(price);
    }
    @GetMapping("/list/byBed/{bed}")
    public Response<List<RoomsResp>> getRoomsByBed(@PathVariable Integer bed) {
        return  roomsService.getRoomsByBed(bed);

    }
    @GetMapping("/list/ByAvaible")
    public Response<List<RoomsResp>> getRoomsByAvaible() {
        return roomsService.getRoomsByAvaible();
    }

}
