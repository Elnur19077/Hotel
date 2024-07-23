package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUPPLIYER", schema = "ORIENT_EHOTEL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Suppliyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "TEL_NUMBER")
    private Long telNumber;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ADRESS", length = 100)
    private String address;

}
