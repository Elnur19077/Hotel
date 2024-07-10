package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@Table(name = "OTEL_INFO")
@Data
@DynamicInsert
public class OtelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adress;
    @Column(name = "MOBILE_NUMBER")
    private  Integer mobilenumber;
    private String email;
    private String socialNetworkName;
    private Date dateOfEstablishment;
    private  Integer numberOfStars;

}
