package az.hotel.dto.response;

import az.hotel.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeComResp {
    private Long id;
    private Integer telNumber;
    private String email;
    private String address;
    private EmployeeResp employeeResp;
    private Date sysDate;
    private Integer avtivity;
}
