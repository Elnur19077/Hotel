package az.hotel.dto.request;

import az.hotel.dto.response.CustomerResp;
import lombok.Data;

import java.sql.Date;

@Data
public class ReqCustomerAdd {
    private Long id;
    private Integer telNumber;
    private String email;
    private String address;
    private Date sysDate;
   private Long customerId;
    private  Integer activity;
}
