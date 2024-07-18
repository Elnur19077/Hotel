package az.hotel.dto.response;

import az.hotel.entity.Customer;
import az.hotel.entity.Rooms;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailabityResp {
    private Long id;

    private Date startDate;

    private Date endDate;
   private  RoomsResp roomsResp;
   private  CustomerResp customerResp;
    private    Integer availabity;
}


