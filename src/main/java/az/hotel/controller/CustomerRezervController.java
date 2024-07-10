package az.hotel.controller;

import az.hotel.dto.request.ReqCustomerRez;
import az.hotel.dto.response.CustomerRezResp;
import az.hotel.dto.response.Response;
import az.hotel.service.CustomerRezervService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rezerv")
@AllArgsConstructor
public class CustomerRezervController {
    private final CustomerRezervService customerRezervService;

    @GetMapping("/list")
    public Response<List<CustomerRezResp>> getCustomerRezList() {
        return customerRezervService.getAllRezervInfo();

    }

    @GetMapping("/{id}")
    public Response<CustomerRezResp> getById(@PathVariable Long id) {
        return customerRezervService.getById(id);
    }

    @GetMapping("/listActive")
    public Response<List<CustomerRezResp>> getAllRezervInfo() {
        return customerRezervService.getAllActiveRezervation();
    }

    @PostMapping("/create")
    public  Response<CustomerRezResp> addRez(@RequestBody ReqCustomerRez reqCustomerRez){
        return  customerRezervService.addrez(reqCustomerRez);
    }
    @PutMapping("/uptade")
public  Response<CustomerRezResp> uptadeRez(@RequestBody ReqCustomerRez reqCustomerRez){
        return  customerRezervService.uptadeRez(reqCustomerRez);
    }
@PutMapping("/delete/{id}")
    public  Response deleteRez(@PathVariable Long id){
        return customerRezervService.deleteRez(id);
}





}
