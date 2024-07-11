package az.hotel.controller;

import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.Response;
import az.hotel.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public Response<List<EmployeeResp>> getEmployees() {
        return employeeService.getAllemployee();
    }
}
