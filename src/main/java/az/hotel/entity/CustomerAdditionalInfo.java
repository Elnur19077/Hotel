package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@Table(name = "CUSTOMER_ADDITIONAL_INFO")
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_add_seq")
    @SequenceGenerator(name = "customer_add_seq", sequenceName = "CUSTOMER_ADD_SEQ", allocationSize = 1)
    private Long id;
    private Integer telNumber;
    private String email;
    private String address;
    @CreationTimestamp
    private Date sysDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private  Integer activity;
}
