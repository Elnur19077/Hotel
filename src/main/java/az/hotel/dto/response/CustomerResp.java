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

public class CustomerResp {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private Date dob;

}
