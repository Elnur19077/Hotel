package az.hotel.service;

import az.hotel.dto.request.ReqEmployee;
import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Response<List<EmployeeResp>> getAllemployee();
    public EmployeeResp convertToEmployeeResp(Employee employee);

    Response<List<EmployeeResp>> getActiveemployees();

    Response<List<EmployeeResp>> findByNameAndSurname(String name, String surname);

    Response<EmployeeResp> createEmployee(ReqEmployee reqEmployee);

    Response<EmployeeResp> uptadeEmployee(ReqEmployee reqEmployee);

    Response<EmployeeResp> deleteEmploye(Long id);
}
