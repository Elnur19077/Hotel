package az.hotel.controller;

import az.hotel.dto.request.ReqRoomFinance;
import az.hotel.dto.response.Response;
import az.hotel.dto.response.RoomFinanceResp;
import az.hotel.service.RoomFinanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class RoomFinanceContoller {

    private final RoomFinanceService roomFinanceService;

    @PostMapping("/create")
    public Response<RoomFinanceResp> addRoomFinance(@RequestBody ReqRoomFinance reqRoomFinance) {
        return  roomFinanceService.addRoomFinance(reqRoomFinance);
    }
    @GetMapping("/list")
    public Response<List<RoomFinanceResp>> getAllRoomFinance() {
        return  roomFinanceService.getAllRoomFinance();
    }
    @GetMapping("/listCustomer/{id}")
    public Response<RoomFinanceResp> getAllRoomFinanceByCustomerId(@PathVariable Long id) {
        return  roomFinanceService.getAllFinanceByCustomerId(id);
    }
    @GetMapping("/listPayment/{id}")
    public Response<List<RoomFinanceResp>> getAllRoomFinanceByPaymentId(@PathVariable Long id) {
        return  roomFinanceService.getAllRoomFinanceByPaymentId(id);
    }
}
