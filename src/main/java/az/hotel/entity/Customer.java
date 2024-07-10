package az.hotel.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@Table(name = "CUSTOMER")
@Data
@DynamicInsert

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private Date dob;
    @CreationTimestamp
    private Date sysDate;

    private Integer activity;

}
