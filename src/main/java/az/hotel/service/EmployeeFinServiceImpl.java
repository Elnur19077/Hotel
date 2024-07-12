package az.hotel.service;

import az.hotel.dto.request.ReqEmployeeFin;
import az.hotel.dto.response.EmployeeFinResp;
import az.hotel.dto.response.EmployeeResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.Employee;
import az.hotel.entity.EmployeeFinInfo;
import az.hotel.enums.EnumAvailableStatus;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.EmployeeFinRepository;
import az.hotel.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeFinServiceImpl implements EmployeeFinService{
    private  final EmployeeFinRepository employeeFinRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Response<List<EmployeeFinResp>> getAllEmployeeFinancialInfos() {
        Response<List<EmployeeFinResp>> response = new Response<>();
        try {
            List<EmployeeFinInfo> employeeFinInfos = employeeFinRepository.findAll();
            if (employeeFinInfos.isEmpty()) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<EmployeeFinResp> employeeFinResps = employeeFinInfos.stream().map(this::convertEmployeeFinInfo).toList();
            response.setT(employeeFinResps);
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
    public Response<EmployeeFinResp> getEmployeeFinancialInfoById(Long id) {
        Response<EmployeeFinResp> response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Employee financial info ID is null", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeFinInfo employeeFinInfo = employeeFinRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (employeeFinInfo == null) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeFinResp employeeFinResp = convertEmployeeFinInfo(employeeFinInfo);
            response.setT(employeeFinResp);
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
    public Response<List<EmployeeFinResp>> getAllActiveEmployeeFinancialInfos() {
        Response<List<EmployeeFinResp>> response = new Response<>();
        try {
            List<EmployeeFinInfo> employeeFinInfos = employeeFinRepository.findAllByActivity(EnumAvailableStatus.ACTIVE.getValue());
            if (employeeFinInfos.isEmpty()) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            List<EmployeeFinResp> employeeFinResps = employeeFinInfos.stream().map(this::convertEmployeeFinInfo).toList();
            response.setT(employeeFinResps);
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
    public Response<EmployeeFinResp> addEmployeeFinancialInfo(ReqEmployeeFin reqEmployeeFin) {
        Response<EmployeeFinResp> response = new Response<>();
        try {
            Long employeeId=reqEmployeeFin.getEmployeeId();
            Double salary = reqEmployeeFin.getSalary();
            Double bonus = reqEmployeeFin.getBonus();
            if (salary == null || bonus == null || employeeId == null) {
                throw new CustomerException("Salary or bonus is null", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            Employee employee = employeeRepository.findEmployeeByIdAndActivity(reqEmployeeFin.getEmployeeId(), EnumAvailableStatus.ACTIVE.getValue());
            if (employee == null) {
                throw new CustomerException("Employee not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeFinInfo employeeFinInfo = EmployeeFinInfo.builder()
                    .salary(salary)
                    .bonus(bonus)
                    .employee(employee)
                    .build();
            employeeFinRepository.save(employeeFinInfo);
            EmployeeFinResp employeeFinResp = convertEmployeeFinInfo(employeeFinInfo);
            response.setT(employeeFinResp);
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
    public Response<EmployeeFinResp> updateEmployeeFinancialInfo(ReqEmployeeFin reqEmployeeFin) {
        Response<EmployeeFinResp> response = new Response<>();
        try {
            Long id = reqEmployeeFin.getId();
            if (id == null) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            EmployeeFinInfo employeeFinInfo = employeeFinRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (employeeFinInfo == null) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            employeeFinInfo.setSalary(reqEmployeeFin.getSalary());
            employeeFinInfo.setBonus(reqEmployeeFin.getBonus());
            employeeFinRepository.save(employeeFinInfo);
            EmployeeFinResp employeeFinResp = convertEmployeeFinInfo(employeeFinInfo);
            response.setT(employeeFinResp);
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
    public Response deleteEmployeeFinancialInfo(Long id) {
        Response response = new Response<>();
        try {
            if (id == null) {
                throw new CustomerException("Invalid data", ExceptionConstant.INVALID_REQUEST_DATA);
            }
            EmployeeFinInfo employeeFinInfo = employeeFinRepository.findByIdAndActivity(id, EnumAvailableStatus.ACTIVE.getValue());
            if (employeeFinInfo == null) {
                throw new CustomerException("Employee financial info not found", ExceptionConstant.CUSTOMER_NOT_FOUND);
            }
            employeeFinInfo.setActivity(EnumAvailableStatus.DEACTIVE.getValue());
            employeeFinRepository.save(employeeFinInfo);
            response.setT(employeeFinInfo);
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

    private EmployeeFinResp convertEmployeeFinInfo(EmployeeFinInfo employeeFinInfo) {
        EmployeeResp employeeResp = EmployeeResp.builder()
                .name(employeeFinInfo.getEmployee().getName())
                .surname(employeeFinInfo.getEmployee().getSurname())
                .fatherName(employeeFinInfo.getEmployee().getFatherName())
                .photo(employeeFinInfo.getEmployee().getPhoto())
                .build();
        return EmployeeFinResp.builder()
                .id(employeeFinInfo.getId())
                .salary(employeeFinInfo.getSalary())
                .bonus(employeeFinInfo.getBonus())
                .employeeResp(employeeResp)
                .build();
    }
}
