package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@Table(name = "EMPLOYEE_COMMUNICATION")
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCommunication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeecom_seq")
    @SequenceGenerator(name = "employeecom_seq", sequenceName = "EMPLOYEE_COM_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "TEL_NUMBER")
    private Integer telNumber;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @CreationTimestamp
    @Column(name = "SYS_DATE")
    private Date sysDate;
    @ColumnDefault(value = "1")
    private Integer activity;
}
