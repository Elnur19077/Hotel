package az.hotel.service;

import az.hotel.dto.request.ReqCustomerAdd;
import az.hotel.dto.response.CustomerAddResp;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerAddServiceImpl implements CustomerAddService {
    private final CustomerAddRepository customerAddRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Response<List<CustomerAddResp>> getAllCustomerAdditionalInfo() {
        Response<List<CustomerAddResp>> response = new Response<>();
        try {
            List<CustomerAdditionalInfo> customers = (List<CustomerAdditionalInfo>) customerAddRepository.findAll();
            if (customers.isEmpty()) {
                throw new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<CustomerAddResp> customerResps = customers.stream().map(this::convertCustomerAdditionalInfo).toList();
            response.setT(customerResps);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public CustomerAddResp convertCustomerAdditionalInfo(CustomerAdditionalInfo customerAdditionalInfo) {
        CustomerResp customerResp = CustomerResp.builder()
                .name(customerAdditionalInfo.getCustomer().getName())
                .surname(customerAdditionalInfo.getCustomer().getSurname())
                .build();
        return CustomerAddResp.builder().
                id(customerAdditionalInfo.getId()).
                telNumber(customerAdditionalInfo.getTelNumber()).
                email(customerAdditionalInfo.getEmail()).
                address(customerAdditionalInfo.getAddress()).
                customerResp(customerResp).
                build();
    }

    @Override
    public Response<CustomerAddResp> getById(Long id) {
        Response<CustomerAddResp> response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Customer ID is null", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            CustomerAdditionalInfo customerAdditionalInfo = customerAddRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (customerAdditionalInfo == null) {
                throw new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            CustomerAddResp customerAddResp = convertCustomerAdditionalInfo(customerAdditionalInfo);
            response.setT(customerAddResp);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public Response<List<CustomerAddResp>> getAllActiveCustomerAdditionalInfo() {
        Response<List<CustomerAddResp>> response = new Response<>();
        try {
            List<CustomerAdditionalInfo> customerAdditionalInfos = customerAddRepository.findAllByActivity(EnumAvailableStatus.ACTIVE.getValue());
            if (customerAdditionalInfos.isEmpty()) {
                throw new CustomerException("Customer additional info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<CustomerAddResp> customerAddResps = customerAdditionalInfos.stream().map(this::convertCustomerAdditionalInfo).toList();
            response.setT(customerAddResps);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public Response<CustomerAddResp> addCustomerAdditionalInfo(ReqCustomerAdd reqCustomerAdd) {
        Response<CustomerAddResp> response = new Response<>();

        try {
            Integer telNumber = reqCustomerAdd.getTelNumber();
            if (telNumber == null) {
                throw new CustomerException("Customer telNumber is null", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            Customer customer = customerRepository.findCustomerByIdAndActivity(reqCustomerAdd.getCustomerId(), EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            CustomerAdditionalInfo customerAdditionalInfo = CustomerAdditionalInfo.builder().
                    telNumber(telNumber).
                    email(reqCustomerAdd.getEmail()).
                    address(reqCustomerAdd.getAddress()).
                    customer(customer).


                    build();
            customerAddRepository.save(customerAdditionalInfo);
            CustomerAddResp customerAddResp = convertCustomerAdditionalInfo(customerAdditionalInfo);
            response.setT(customerAddResp);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public Response<CustomerAddResp> updateCustomerAdditionalInfo(ReqCustomerAdd reqCustomerAdd) {
        Response<CustomerAddResp> response = new Response<>();
        try {
            Long id = reqCustomerAdd.getId();
            if (id == null) {
                throw new CustomerException("Customer additional info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            CustomerAdditionalInfo customerAdditionalInfo = customerAddRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());

            if (customerAdditionalInfo == null) {
                throw new CustomerException("Customer additional info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            customerAdditionalInfo.setTelNumber(reqCustomerAdd.getTelNumber());
            customerAdditionalInfo.setEmail(reqCustomerAdd.getEmail());
            customerAdditionalInfo.setAddress(reqCustomerAdd.getAddress());

            CustomerAddResp customerAddResp = convertCustomerAdditionalInfo(customerAdditionalInfo);
            response.setT(customerAddResp);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public Response deleteCustomerAdditionalInfo(Long id) {
        Response response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Invalid data", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            CustomerAdditionalInfo customerAdditionalInfo = customerAddRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (customerAdditionalInfo == null) {
                throw new CustomerException("Customer additional info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            customerAdditionalInfo.setActivity(EnumAvailableStatus.DEACTIVE.getValue());
            customerAddRepository.save(customerAdditionalInfo);
            response.setT(customerAdditionalInfo);
            response.setStatus(RespStatus.getSuccessMessage());
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
    public Response<List<CustomerAdditionalInfo>> getCustomerAdditionalInfoByCustomerName(String name) {
        Response<List<CustomerAdditionalInfo>> response = new Response<>();
        try {
            List<CustomerAdditionalInfo> customerAdditionalInfos = customerAddRepository.findCustomerAdditionalInfoByCustomerName(name);
            if (customerAdditionalInfos.isEmpty()) {
                throw new CustomerException("Customer additional info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            response.setT(customerAdditionalInfos);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }


}
