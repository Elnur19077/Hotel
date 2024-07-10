package az.hotel.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddResp {
    private Long id;
    private Integer telNumber;
    private String email;
    private String address;
    private Date sysDate;
    private CustomerResp customerResp;
    private  Integer activity;
}
