package az.hotel.service;

import az.hotel.dto.response.CustomerResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Response<List<CustomerResp>> getCustomers();

    public Response<List<CustomerResp>> getCustomersById(Integer id);

    public void sendEmail(String to, String subject, String text);


    public CustomerResp updateCustomer(Integer id, CustomerResp customer);

    public CustomerResp convertCustomer(Customer customer);

    public Customer createCustomer(Customer customer);

    public void deleteCustomer(Integer id);

    public List<CustomerResp> getCustomersByActivity(Integer activity);



}
