package az.hotel.service;

import az.hotel.dto.request.ReqCustomerRez;
import az.hotel.dto.response.CustomerRezResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.CustomerRezervInfo;
import az.hotel.enums.EnumAvailableStatus;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.CustomerRezRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerRezervServiceImpl implements CustomerRezervService {
    private final CustomerRezRepository customerRezRepository;

    @Override
    public Response<List<CustomerRezResp>> getAllRezervInfo() {
        Response<List<CustomerRezResp>> response = new Response<>();
        try {
            List<CustomerRezervInfo> customers = customerRezRepository.findAll();
            if (customers.isEmpty()) {
                throw new CustomerException("Customer not found", ExceptionConstant.CUSTOMER_NOT_FOUND);

            }
            List<CustomerRezResp> customerResps = customers.stream().map(this::convertCustomerRez).toList();
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
    public CustomerRezResp convertCustomerRez(CustomerRezervInfo customerRezervInfo) {
        return CustomerRezResp.builder().
                id(customerRezervInfo.getId()).
                enteryDate(customerRezervInfo.getEnteryDate()).
                exitDate(customerRezervInfo.getExitDate()).
                numbersOfBed(customerRezervInfo.getNumbersOfBed()).
                payment(customerRezervInfo.getPayment()).
                roomsType(customerRezervInfo.getRoomsType()).
                build();
    }

    @Override
    public Response<CustomerRezResp> getById(Long id) {
        Response<CustomerRezResp> response = new Response<>();
        try {
            Optional<CustomerRezervInfo> customerRezervInfoOptional = customerRezRepository.findById(id);
            if (customerRezervInfoOptional.isPresent()) {
                CustomerRezervInfo customerRezervInfo = customerRezervInfoOptional.get();
                CustomerRezResp customerRezResp = convertCustomerRez(customerRezervInfo);
                response.setT(customerRezResp);
                response.setStatus(RespStatus.getSuccessMessage());
            } else {
                throw new CustomerException("Customer reservation not found with ID: " + id, ExceptionConstant.CUSTOMER_NOT_FOUND);
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
    public Response<List<CustomerRezResp>> getAllActiveRezervation() {
        Response<List<CustomerRezResp>> response = new Response<>();
        try {
            List<CustomerRezervInfo> customerRezervInfos = customerRezRepository.findAllByActivity(EnumAvailableStatus.ACTIVE.getValue());
            if (customerRezervInfos.isEmpty()) {
                throw new CustomerException("Customer reservation not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<CustomerRezResp> customerRezResps = customerRezervInfos.stream().map(this::convertCustomerRez).toList();
            response.setT(customerRezResps);
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
    public Response<CustomerRezResp> addrez(ReqCustomerRez reqCustomerRez) {
        Response<CustomerRezResp> response = new Response<>();
        try {
            Date enretyDate = reqCustomerRez.getEnteryDate();
            Date exitDate = reqCustomerRez.getExitDate();
            if (enretyDate == null || exitDate == null) {
                throw new CustomerException("Entery Date or exitDate is null", ExceptionConstant.INVALID_REQUEST_DATA);
            } else if (enretyDate.after(exitDate)) {
                throw new CustomerException("Entery Date is after exitDate", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            CustomerRezervInfo customerRezResp = CustomerRezervInfo.builder().
                    enteryDate((java.sql.Date) enretyDate).
                    exitDate((java.sql.Date) exitDate).
                    numbersOfBed(reqCustomerRez.getNumbersOfBed()).
                    payment(reqCustomerRez.getPayment()).
                    roomsType(reqCustomerRez.getRoomsType()).
                    build();
            customerRezRepository.save(customerRezResp);
            CustomerRezResp customerRezResp1 = convertCustomerRez(customerRezResp);
            response.setT(customerRezResp1);
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
    public Response<CustomerRezResp> uptadeRez(ReqCustomerRez reqCustomerRez) {
        Response<CustomerRezResp> response = new Response<>();
        try {
            Long id = reqCustomerRez.getId();
            if (id == null) {
                throw new CustomerException("Customer reservation not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            CustomerRezervInfo customerRezervInfo = customerRezRepository.findAllByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());

            if (customerRezervInfo == null) {
                throw new CustomerException("Customer reservation not found", ExceptionConstant.CUSTOMER_NOT_FOUND);

            }
            customerRezervInfo.setEnteryDate(reqCustomerRez.getEnteryDate());
            customerRezervInfo.setExitDate(reqCustomerRez.getExitDate());
            customerRezervInfo.setNumbersOfBed(reqCustomerRez.getNumbersOfBed());
            customerRezervInfo.setPayment(reqCustomerRez.getPayment());
            customerRezervInfo.setRoomsType(reqCustomerRez.getRoomsType());
            CustomerRezResp customerRezResp = convertCustomerRez(customerRezervInfo);
            response.setT(customerRezResp);
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
    public Response deleteRez(Long id) {
        Response response=new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Invalid data ", ExceptionConstant.INVALID_REQUEST_DATA);
            }
           CustomerRezervInfo customerRezervInfo= customerRezRepository.findAllByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (customerRezervInfo==null) {
                throw new CustomerException("Customer  not found", ExceptionConstant.CUSTOMER_NOT_FOUND);

            }
            customerRezervInfo.setActivity(EnumAvailableStatus.DEACTIVE.getValue());
            customerRezRepository.save(customerRezervInfo);
            response.setT(customerRezervInfo);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

}





