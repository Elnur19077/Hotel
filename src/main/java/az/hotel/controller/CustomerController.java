package az.hotel.controller;

import az.hotel.dto.response.CustomerResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.Customer;
import az.hotel.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("list")
    public Response<List<CustomerResp>> getCustomers() {
        return this.customerService.getCustomers();
    }

    @PostMapping("id")
    public Response<List<CustomerResp>> getCustomerById(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        return this.customerService.getCustomersById(id);


    }
    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        customerService.sendEmail(to, subject, text);
        return "E-poçt göndərildi!";
    }



    @PutMapping("{id}")
    public CustomerResp updateCustomer(@PathVariable Integer id, @RequestBody CustomerResp customerResp) {
        return customerService.updateCustomer(id, customerResp);
    }

    @PostMapping("save")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/byActivity")
    public  List<CustomerResp> getByActivity()
    {
        return customerService.getCustomersByActivity(2);
    }
}

