package az.hotel.dto.request;

import lombok.Data;

import java.sql.Date;
@Data
public class ReqEmployeeFin {
    private  Long id;
    private Double salary;
    private  Double bonus;
    private Long employeeId;
    private Date sysDate;
    private Integer activity;
}
