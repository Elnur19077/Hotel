package az.hotel.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRezResp {
    private Long id;
    private Date enteryDate;
    private  Date exitDate;
    private  String roomsType;
    private Integer numbersOfBed;
    private  Integer payment;
    private Date sysDate;
    private  Integer activity;
}
