package az.hotel.service;

import az.hotel.dto.request.ReqEmployeeFin;
import az.hotel.dto.response.EmployeeFinResp;
import az.hotel.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeFinService {

    Response<List<EmployeeFinResp>> getAllEmployeeFinancialInfos();
    Response<EmployeeFinResp> getEmployeeFinancialInfoById(Long id);
    Response<List<EmployeeFinResp>> getAllActiveEmployeeFinancialInfos();
    Response<EmployeeFinResp> addEmployeeFinancialInfo(ReqEmployeeFin reqEmployeeFin);
    Response<EmployeeFinResp> updateEmployeeFinancialInfo(ReqEmployeeFin reqEmployeeFin);
    Response deleteEmployeeFinancialInfo(Long id);
}
