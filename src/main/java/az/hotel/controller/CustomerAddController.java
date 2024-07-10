package az.hotel.controller;

import az.hotel.dto.request.ReqCustomerAdd;
import az.hotel.dto.response.CustomerAddResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.CustomerAdditionalInfo;
import az.hotel.service.CustomerAddService;
import az.hotel.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("add")
@RequiredArgsConstructor

public class CustomerAddController {
    private final CustomerAddService customerAddService;

    @GetMapping("/list")
    public Response<List<CustomerAddResp>> getAllCustomerAdditionalInfo(){
        return customerAddService.getAllCustomerAdditionalInfo();
    }
    @GetMapping("/list/{id}")
    public Response<CustomerAddResp> getById(@PathVariable Long id){
        return customerAddService.getById(id);

    }
    @GetMapping("/listActive")
    public Response<List<CustomerAddResp>> getAllActiveCustomerAdditionalInfo(){
        return customerAddService.getAllActiveCustomerAdditionalInfo();
    }
    @PostMapping("/create")
    public Response<CustomerAddResp> addCustomerAdditionalInfo(@RequestBody  ReqCustomerAdd reqCustomerAdd){
        return customerAddService.addCustomerAdditionalInfo(reqCustomerAdd);
    }
    @PutMapping("/uptade")
    public Response<CustomerAddResp> updateCustomerAdditionalInfo(@RequestBody ReqCustomerAdd reqCustomerAdd){
        return customerAddService.updateCustomerAdditionalInfo(reqCustomerAdd);
    }
    @PutMapping("/delete/{id}")
    public Response deleteCustomerAdditionalInfo(@PathVariable Long id){
        return customerAddService.deleteCustomerAdditionalInfo(id);
    }
@GetMapping("/find/{name}")
Response<List<CustomerAdditionalInfo>> getCustomerAdditionalInfoByCustomerName(@PathVariable String name){
        return customerAddService.getCustomerAdditionalInfoByCustomerName(name);
}
}
