package az.hotel.service;

import az.hotel.dto.request.ReqCustomerRez;
import az.hotel.dto.response.CustomerResp;
import az.hotel.dto.response.CustomerRezResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.CustomerRezervInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerRezervService {
    Response<List<CustomerRezResp>> getAllRezervInfo();

    public CustomerRezResp convertCustomerRez(CustomerRezervInfo customerRezervInfo);
    public Response<CustomerRezResp> getById(Long id);

    Response<List<CustomerRezResp>> getAllActiveRezervation();

    Response<CustomerRezResp> addrez(ReqCustomerRez reqCustomerRez);

    Response<CustomerRezResp> uptadeRez(ReqCustomerRez reqCustomerRez);

    Response deleteRez(Long id);
}
