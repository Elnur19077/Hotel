package az.hotel.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ReqEmployeeCom {
    private Long id;
    private Integer telNumber;
    private String email;
    private String address;
    private Long employeeID;
    private Date sysDate;
    private Integer activity;
}

