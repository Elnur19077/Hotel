package az.hotel.service;

import az.hotel.dto.request.ReqEmployeeCom;
import az.hotel.dto.response.EmployeeComResp;
import az.hotel.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeComService {
    Response<List<EmployeeComResp>> getAllEmployeeCommunications();
    Response<EmployeeComResp> getEmployeeCommunicationById(Long id);
    Response<List<EmployeeComResp>> getAllActiveEmployeeCommunications();
    Response<EmployeeComResp> addEmployeeCommunication(ReqEmployeeCom reqEmployeeCom);
    Response<EmployeeComResp> updateEmployeeCommunication(ReqEmployeeCom reqEmployeeCom);
    Response <EmployeeComResp> deleteEmployeeCommunication(Long id);
}
