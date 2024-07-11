package az.hotel.controller;

import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import az.hotel.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/listActive")
    public Response<List<EmployeeResp>> getActiveEmployees() {
        return employeeService.getActiveemployees();
    }
    @GetMapping("/find/{name}/{surname")
    public Response<List<EmployeeResp>> findEmployee(@PathVariable String name, @PathVariable String surname) {
        return employeeService.findByNameAndSurname(name,surname);
    }
    @PostMapping("/create")
Response<EmployeeResp> createEmploye(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
}
