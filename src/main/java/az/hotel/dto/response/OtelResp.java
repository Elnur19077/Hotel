package az.hotel.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtelResp {
    private String name;
    private String adress;
    private  Integer mobilenumber;
    private String email;
    private String socialNetworkName;
    private Date dateOfEstablishment;
    private  Integer numberOfStars;
}
