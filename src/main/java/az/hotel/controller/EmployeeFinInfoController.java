package az.hotel.controller;

import az.hotel.dto.request.ReqEmployeeFin;
import az.hotel.dto.response.EmployeeFinResp;
import az.hotel.dto.response.Response;
import az.hotel.service.EmployeeFinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("financial")
@RequiredArgsConstructor
public class EmployeeFinInfoController {
    private final EmployeeFinService employeeFinService;

    @GetMapping("/list")
    Response<List<EmployeeFinResp>> getAllEmployeeFinancialInfos(){
        return employeeFinService.getAllEmployeeFinancialInfos();
    }
    @GetMapping("/active")
    Response<List<EmployeeFinResp>> getAllActiveEmployeeFinancialInfos(){
        return employeeFinService.getAllActiveEmployeeFinancialInfos();
    }
    @GetMapping("/list/{id}")
    Response<EmployeeFinResp> getEmployeeFinancialInfoById(@PathVariable Long id){
        return employeeFinService.getEmployeeFinancialInfoById(id);
    }
    @PostMapping("/create")
    Response<EmployeeFinResp> addEmployeeFinancialInfo(@RequestBody ReqEmployeeFin reqEmployeeFin){
        return employeeFinService.addEmployeeFinancialInfo(reqEmployeeFin);
    }
    @PutMapping("/uptade")
    Response<EmployeeFinResp> updateEmployeeFinancialInfo(@RequestBody ReqEmployeeFin reqEmployeeFin){
        return employeeFinService.updateEmployeeFinancialInfo(reqEmployeeFin);
    }
    @PutMapping("/delete/{id}")
    Response<EmployeeFinResp> deleteEmployeeFinancialInfo(@PathVariable Long id){
        return employeeFinService.deleteEmployeeFinancialInfo(id);
    }




}
