package az.hotel.service;

import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import az.hotel.exception.CustomerException;
import az.hotel.exception.EmployeeException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Response<List<EmployeeResp>> getAllemployee() {
        Response<List<EmployeeResp>> response = new Response<>();
        try {
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                throw new EmployeeException("Employee not found", ExceptionConstant.EMPLOYEE_NOT_FOUND);
            }
            List<EmployeeResp> employeeResps=employees.stream().map(this::convertToEmployeeResp).toList();
            response.setT(employeeResps);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (EmployeeException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public EmployeeResp convertToEmployeeResp(Employee employee) {
        return EmployeeResp.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .fatherName(employee.getFatherName())
                .photo(employee.getPhoto())
                .build();
    }

}
