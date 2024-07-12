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
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE_FINANCIAL_INFO")
public class EmployeeFinInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeefin_seq")
    @SequenceGenerator(name = "employeefin_seq", sequenceName = "EMPLOYEE_FIN_SEQ", allocationSize = 1)
    private  Long id;
    private Double salary;
    private  Double bonus;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @CreationTimestamp
    @Column(name = "SYS_DATE")
    private Date sysDate;
    @ColumnDefault(value = "1")
    private Integer activity;
}
