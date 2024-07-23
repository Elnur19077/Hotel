package az.hotel.service;

import az.hotel.dto.request.ReqRoomFinance;
import az.hotel.dto.response.Response;
import az.hotel.dto.response.RoomFinanceResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomFinanceService {
    Response<RoomFinanceResp> addRoomFinance(ReqRoomFinance reqRoomFinance);

    Response<RoomFinanceResp> updateRoomFinance(ReqRoomFinance reqRoomFinance);

    Response<List<RoomFinanceResp>> getAllRoomFinance();

    Response<RoomFinanceResp> getAllFinanceByCustomerId(Long id);

    Response<List<RoomFinanceResp>> getAllRoomFinanceByPaymentId(Long id);
}
