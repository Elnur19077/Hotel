package az.hotel.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ReqCustomerRez {

    private Long id;
    private Date enteryDate;
    private  Date exitDate;
    private  Long customerId;
    private  String roomsType;
    private Integer numbersOfBed;
    private  Integer payment;
    private Date sysDate;
    private  Integer activity;
}
