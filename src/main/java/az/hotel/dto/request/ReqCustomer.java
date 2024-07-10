package az.hotel.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class ReqCustomer {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private Date dob;

}
