package az.hotel.service;

import az.hotel.dto.response.CustomerResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.Customer;
import az.hotel.entity.CustomerAdditionalInfo;
import az.hotel.enums.EnumAvailableStatus;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.CustomerAddRepository;
import az.hotel.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final  JavaMailSender mailSender;
    private final CustomerAddRepository customerAddRepository;


    @Override
    public Response<List<CustomerResp>> getCustomers() {
        Response<List<CustomerResp>> response = new Response<>();
        try {

            List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                throw  new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);

            }
            List<CustomerResp> customerResps = customers.stream().map(this::convertCustomer).toList();
            response.setT(customerResps);
            response.setStatus(RespStatus.getSuccessMessage());


        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));
        }catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }

        return response;
    }

    @Override
    public Response<List <CustomerResp> >getCustomersById(Integer id) {

        Response<List<CustomerResp>> response = new Response<>();

        try {
            if (id == null) {
                throw new CustomerException("Customer ID is null", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }

            List<CustomerResp> customers = new ArrayList<>();
            Optional<Customer> customerOptional = customerRepository.findById(id);

            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                CustomerResp customerResp = new CustomerResp();
                customerResp.setId(customer.getId());
                customerResp.setName(customer.getName());
                customerResp.setSurname(customer.getSurname());
                customerResp.setFatherName(customer.getFatherName());
                customerResp.setDob(customer.getDob());
                customers.add(customerResp);

                response.setT(customers);
                response.setStatus(RespStatus.getSuccessMessage());
            } else {
                throw new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
        } catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }

        return response;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("eli-eliyev-2025@mail.ru");
        mailSender.send(message);
    }


    @Override
    public CustomerResp updateCustomer(Integer id,CustomerResp customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setSurname(customer.getSurname());
            customerToUpdate.setFatherName(customer.getFatherName());
            customerToUpdate.setDob(customer.getDob());
           Customer uptated = customerRepository.save(customerToUpdate);

           return convertCustomer(uptated);

        }
        else {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }

    }

    @Override
    public CustomerResp convertCustomer(Customer customer) {
       return CustomerResp.builder().
               id(customer.getId()).
               name(customer.getName()).
               surname(customer.getSurname()).
               fatherName(customer.getFatherName()).
               dob(customer.getDob()).
               build();
    }

    @Override

    public Customer createCustomer(Customer customer) {

        return  customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setActivity(0);
            customerRepository.save(customer);
        } else {

            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
    }

    @Override
    public List<CustomerResp> getCustomersByActivity(Integer activity) {
        List<Customer> customers = customerRepository.getCustomersByActivity(0);
        List<CustomerResp> customerResps = new ArrayList<>();

        for (Customer customer : customers) {
            customerResps.add(convertCustomer(customer));
        }

        return customerResps;
    }



}
