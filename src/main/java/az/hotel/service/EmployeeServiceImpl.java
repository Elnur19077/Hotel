package az.hotel.service;

import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import az.hotel.enums.EnumAvailableStatus;
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

    @Override
    public Response<List<EmployeeResp>> getActiveemployees() {
        Response<List<EmployeeResp>> response = new Response<>();
        try {
            List<Employee> employees = employeeRepository.findAllByAvtivity(EnumAvailableStatus.ACTIVE.getValue());
        if (employees.isEmpty()) {
            throw new EmployeeException("Employee not found", ExceptionConstant.EMPLOYEE_NOT_FOUND);
        }
        List<EmployeeResp> employeeResps=employees.stream().map(this::convertToEmployeeResp).toList();
        response.setT(employeeResps);
        response.setStatus(RespStatus.getSuccessMessage());
        }catch (EmployeeException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<List<EmployeeResp>> findByNameAndSurname(String name, String surname) {
        Response<List<EmployeeResp>> response = new Response<>();
        try {
            if (name==null || surname==null) {
                throw new CustomerException("Name and surname must be declared",ExceptionConstant.INVALID_REQUEST_DATA);
            }
            List<Employee> employees = employeeRepository.findAllByNameAndSurname(name, surname);
            if (employees.isEmpty()) {
                throw new EmployeeException("Employee not found", ExceptionConstant.EMPLOYEE_NOT_FOUND);
            }
            List<EmployeeResp> employeeResps=employees.stream().map(this::convertToEmployeeResp).toList();
            response.setT(employeeResps);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (EmployeeException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<EmployeeResp> createEmployee(Employee employee) {
        Response<EmployeeResp> response = new Response<>();
        try {
            String name = employee.getName();
            String surname = employee.getSurname();
            String fatherName = employee.getFatherName();
            if (name==null || surname==null || fatherName==null) {
                throw new CustomerException("Name and surname must be declared",ExceptionConstant.INVALID_REQUEST_DATA);
            }
            Employee employee1=Employee.builder()
                    .name(name)
                    .surname(surname)
                    .fatherName(fatherName)
                    .build();
            employeeRepository.save(employee1);
            EmployeeResp employeeResp=convertToEmployeeResp(employee1);
            response.setT(employeeResp);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (EmployeeException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }



}
