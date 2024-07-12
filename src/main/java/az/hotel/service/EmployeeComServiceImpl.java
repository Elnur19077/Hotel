package az.hotel.service;

import az.hotel.dto.request.ReqEmployeeCom;
import az.hotel.dto.response.EmployeeComResp;
import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import az.hotel.entity.EmployeeCommunication;
import az.hotel.enums.EnumAvailableStatus;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.EmployeeComRepository;
import az.hotel.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeComServiceImpl implements EmployeeComService {
    private final EmployeeComRepository employeeComRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Response<List<EmployeeComResp>> getAllEmployeeCommunications() {
        Response<List<EmployeeComResp>> response = new Response<>();
        try {
            List<EmployeeCommunication> employeeCommunications = employeeComRepository.findAll();
            if (employeeCommunications.isEmpty()) {
                throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<EmployeeComResp> employeeComResps = employeeCommunications.stream().map(this::convertEmployeeCommunication).toList();
            response.setT(employeeComResps);
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
    public Response<EmployeeComResp> getEmployeeCommunicationById(Long id) {
        Response<EmployeeComResp> response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Employee communication ID is null", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeCommunication employeeCommunication = employeeComRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (employeeCommunication == null) {
                throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeComResp employeeComResp = convertEmployeeCommunication(employeeCommunication);
            response.setT(employeeComResp);
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
    public Response<List<EmployeeComResp>> getAllActiveEmployeeCommunications() {
        Response<List<EmployeeComResp>> response = new Response<>();
        try {
            List<EmployeeCommunication> employeeCommunications = employeeComRepository.findAllByActivity(EnumAvailableStatus.ACTIVE.getValue());
            if (employeeCommunications.isEmpty()) {
                throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<EmployeeComResp> employeeComResps = employeeCommunications.stream().map(this::convertEmployeeCommunication).toList();
            response.setT(employeeComResps);
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
    public Response<EmployeeComResp> addEmployeeCommunication(ReqEmployeeCom reqEmployeeCom) {
        Response<EmployeeComResp> response = new Response<>();
        try {
            Integer telNumber = reqEmployeeCom.getTelNumber();
            String email = reqEmployeeCom.getEmail();
            if (telNumber == null || email == null) {
                throw new CustomerException("Tel number or email is null", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            Employee employee = employeeRepository.findEmployeeByIdAndActivity(reqEmployeeCom.getEmployeeID(), EnumAvailableStatus.ACTIVE.getValue());
            if (employee == null) {
                throw new CustomerException("Employee not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeCommunication employeeCommunication = EmployeeCommunication.builder()
                    .telNumber(reqEmployeeCom.getTelNumber())
                    .email(reqEmployeeCom.getEmail())
                    .address(reqEmployeeCom.getAddress())
                    .employee(employee)
                    .build();
            employeeComRepository.save(employeeCommunication);
            EmployeeComResp employeeComResp = convertEmployeeCommunication(employeeCommunication);
            response.setT(employeeComResp);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }
        return response;
    }

    @Override
    public Response<EmployeeComResp> updateEmployeeCommunication(ReqEmployeeCom reqEmployeeCom) {
        Response<EmployeeComResp> response = new Response<>();
        try {
            Long id = reqEmployeeCom.getId();
            if (id == null) {
                throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeCommunication employeeCommunication = employeeComRepository.findByIdAndActivity(id,EnumAvailableStatus.ACTIVE.getValue());
            if (employeeCommunication == null) {
                throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            employeeCommunication.setTelNumber(reqEmployeeCom.getTelNumber());
            employeeCommunication.setEmail(reqEmployeeCom.getEmail());
            employeeCommunication.setAddress(reqEmployeeCom.getAddress());
            employeeComRepository.save(employeeCommunication);
            EmployeeComResp employeeComResp = convertEmployeeCommunication(employeeCommunication);
            response.setT(employeeComResp);
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
    public Response deleteEmployeeCommunication(Long id) {
        Response response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Invalid data", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            EmployeeCommunication employeeCommunication = employeeComRepository.findByIdAndActivity(id,EnumAvailableStatus.ACTIVE.getValue());
if (employeeCommunication == null) {
    throw new CustomerException("Employee communication not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
}
            employeeCommunication.setActivity(EnumAvailableStatus.DEACTIVE.getValue());
            employeeComRepository.save(employeeCommunication);
            response.setT(employeeCommunication);
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

    private EmployeeComResp convertEmployeeCommunication(EmployeeCommunication employeeCommunication) {
        EmployeeResp employeeResp = EmployeeResp.builder()
                .name(employeeCommunication.getEmployee().getName())
                .surname(employeeCommunication.getEmployee().getSurname())
                .fatherName(employeeCommunication.getEmployee().getFatherName())
                .photo(employeeCommunication.getEmployee().getPhoto()).
                build();
        return EmployeeComResp.builder()
                .id(employeeCommunication.getId())
                .telNumber(employeeCommunication.getTelNumber())
                .email(employeeCommunication.getEmail())
                .address(employeeCommunication.getAddress())
                .employeeResp(employeeResp)
                .build();
    }
}

