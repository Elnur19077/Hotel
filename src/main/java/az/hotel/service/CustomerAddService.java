package az.hotel.service;

import az.hotel.dto.request.ReqCustomerAdd;
import az.hotel.dto.response.CustomerAddResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.CustomerAdditionalInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAddService {
    public Response<List<CustomerAddResp>> getAllCustomerAdditionalInfo();

    public Response<CustomerAddResp> getById(Long id);

    public Response<List<CustomerAddResp>> getAllActiveCustomerAdditionalInfo();
    public Response<CustomerAddResp> addCustomerAdditionalInfo(ReqCustomerAdd reqCustomerAdd);
    public CustomerAddResp convertCustomerAdditionalInfo(CustomerAdditionalInfo customerAdditionalInfo);
    public Response deleteCustomerAdditionalInfo(Long id);
    public Response<CustomerAddResp> updateCustomerAdditionalInfo(ReqCustomerAdd reqCustomerAdd);

    Response<List<CustomerAdditionalInfo>> getCustomerAdditionalInfoByCustomerName(String name);
}
