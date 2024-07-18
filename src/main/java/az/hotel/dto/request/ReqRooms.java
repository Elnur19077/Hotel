package az.hotel.dto.request;

import lombok.Data;

@Data
public class ReqRooms {
    private Long id;
    private String roomsType;
    private Integer numberOfBed;
    private Integer priceForDay;
    private Integer isEmpty;

}
