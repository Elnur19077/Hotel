package az.hotel.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomsResp {

    private String roomsType;
    private Integer numberOfBed;
    private Integer priceForDay;
    private Integer avaible;


}
