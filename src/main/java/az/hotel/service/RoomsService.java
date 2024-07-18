package az.hotel.service;

import az.hotel.dto.response.Response;
import az.hotel.dto.response.RoomsResp;
import az.hotel.entity.Rooms;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface RoomsService {
    Response<List<RoomsResp>> getAllRooms();

    Response<List<RoomsResp>> getRoomsByType(String roomsType);

    Response<RoomsResp> getRoomsById(Long id);

    Response<List<RoomsResp>> getRoomsByPrice(Integer price);

    Response<List<RoomsResp>> getRoomsByBed(Integer bed);

    Response<List<RoomsResp>> getRoomsByAvaible();
    public  RoomsResp covert(Rooms rooms);
}
