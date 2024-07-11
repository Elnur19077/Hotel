package az.hotel.dto.request;

import lombok.Data;

import java.sql.Date;
@Data
public class ReqEmployee {
    private Long id;
    private  String name;
    private String surname;
    private String fatherName;
    private  byte[] photo;
    private Date sysDate;
    private  Integer avtivity;

}
