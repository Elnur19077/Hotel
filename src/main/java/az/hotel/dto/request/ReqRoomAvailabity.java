package az.hotel.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReqRoomAvailabity {


    private Date startDate;
    private Date endDate;
    private Long roomId;
    private Long customerId;
    private Integer availabity;
}



