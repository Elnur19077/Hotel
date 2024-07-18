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
@Table(name = "CUSTOMER_REZERV_INFO")
@Data
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRezervInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_rez_seq")
    @SequenceGenerator(name = "customer_rez_seq", sequenceName = "CUSTOMER_REZ_SEQ", allocationSize = 1)
    private Long id;

    private Date enteryDate;
    private  Date exitDate;
    private  String roomsType;
    @Column(name = "NUMBER_OF_BEDS", columnDefinition = "NUMBER(10,0) ")
    private Integer numbersOfBed;
    private  Integer payment;
    @ManyToOne
    @JoinColumn(name = "customer_id")
   private Customer customer;
    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Rooms rooms;
    @CreationTimestamp
    @Column(name = "SYS_DATE")
    private Date sysDate;
    private  Integer activity;

}
