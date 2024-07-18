package az.hotel.service;

import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.dto.response.RoomsResp;
import az.hotel.entity.Rooms;
import az.hotel.enums.EnumRoomsAvaibility;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.exception.RoomsExpection;
import az.hotel.repository.RoomsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomsServiceImpl implements RoomsService {
    private final RoomsRepository roomsRepository;


    @Override
    public Response<List<RoomsResp>> getAllRooms() {
        Response<List<RoomsResp>> response = new Response<>();
        try {
            List<Rooms> roomsList = roomsRepository.findAll();
            if (roomsList.isEmpty()) {
                throw new RoomsExpection("NO ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomsResp> roomsResp = roomsList.stream().map(this::covert).toList();
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomsResp>> getRoomsByType(String roomsType) {
        Response<List<RoomsResp>> response = new Response<>();
        try {
            if (roomsType == null) {
                throw new CustomerException("Insert RoomsType", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            List<Rooms> roomsList = roomsRepository.findRoomsByRoomsTypeAndAvaible(roomsType, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (roomsList.isEmpty()) {
                throw new RoomsExpection("NO ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomsResp> roomsResp = roomsList.stream().map(this::covert).toList();
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<RoomsResp> getRoomsById(Long id) {
        Response<RoomsResp> response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Insert RoomsId", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            Rooms rooms = roomsRepository.findRoomsByIdAndAvaible(id, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (rooms == null) {
                throw new RoomsExpection("NO ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            RoomsResp roomsResp = covert(rooms);
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomsResp>> getRoomsByPrice(Integer price) {
        Response<List<RoomsResp>> response = new Response<>();
        try {
            if (price == null) {
                throw new CustomerException("Insert RoomsPrice", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            List<Rooms> roomsList = roomsRepository.findRoomsByPriceForDayAndAvaible(price, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (roomsList.isEmpty()) {
                throw new RoomsExpection("NO ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomsResp> roomsResp = roomsList.stream().map(this::covert).toList();
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomsResp>> getRoomsByBed(Integer bed) {
        Response<List<RoomsResp>> response = new Response<>();
        try {
            if (bed == null) {
                throw new CustomerException("Insert RoomsBed", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            List<Rooms> roomsList = roomsRepository.findRoomsByNumberOfBedAndAvaible(bed, EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (roomsList.isEmpty()) {
                throw new RoomsExpection("NO ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomsResp> roomsResp = roomsList.stream().map(this::covert).toList();
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<RoomsResp>> getRoomsByAvaible() {
        Response<List<RoomsResp>> response = new Response<>();
        try {
            List<Rooms> roomsList = roomsRepository.findRoomsByAvaible(EnumRoomsAvaibility.IS_AVAIBLE.getValue());
            if (roomsList.isEmpty()) {
                throw new RoomsExpection("NO AVAIBLE ROOMS FOUND", ExceptionConstant.NO_ROOMS_DETAILS);
            }
            List<RoomsResp> roomsResp = roomsList.stream().map(this::covert).toList();
            response.setT(roomsResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (RoomsExpection ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public RoomsResp covert(Rooms rooms) {
        return RoomsResp.builder()
                .roomsType(rooms.getRoomsType())
                .priceForDay(rooms.getPriceForDay())
                .numberOfBed(rooms.getNumberOfBed())
                .avaible(rooms.getAvaible())
                .build();
    }


}
