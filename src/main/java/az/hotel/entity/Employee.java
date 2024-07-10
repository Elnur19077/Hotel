package az.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;

import java.sql.Date;

@Entity
@Table(name = "EMPLOYEE")
@Data
@DynamicInsert
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "Employee_SEQ", allocationSize = 1)
    private Long id;
    private  String name;
    private String surname;
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "photo", columnDefinition="BLOB")
private  byte[] photo;
@CreationTimestamp
@Column(name = "SYS_DATE")
private Date sysDate;
    @ColumnDefault(value = "1")
private  Integer avtivity;
}
