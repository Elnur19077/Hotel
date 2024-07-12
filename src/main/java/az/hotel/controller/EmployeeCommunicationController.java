package az.hotel.controller;

import az.hotel.dto.request.ReqEmployeeCom;
import az.hotel.dto.response.EmployeeComResp;
import az.hotel.dto.response.Response;
import az.hotel.service.EmployeeComService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("communication")
@RequiredArgsConstructor
public class EmployeeCommunicationController {
    private final EmployeeComService employeeComService;
    @PostMapping("/create")
    Response<EmployeeComResp> addEmployeeCommunication(@RequestBody  ReqEmployeeCom reqEmployeeCom){
        return employeeComService.addEmployeeCommunication(reqEmployeeCom);
    }
}
