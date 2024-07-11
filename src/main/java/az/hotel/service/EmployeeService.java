package az.hotel.service;

import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Response<List<EmployeeResp>> getAllemployee();
    public EmployeeResp convertToEmployeeResp(Employee employee);
}
