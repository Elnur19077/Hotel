package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "ROOMS")
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_seq")
    @SequenceGenerator(name = "rooms_seq", sequenceName = "ROOMS_COM_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "ROOMS_TYPE")
    private String roomsType;
    @Column(name = "NUMBER_OF_BED")
    private Integer numberOfBed;
    @Column(name = "PRICE_FOR_DAY")
    private Integer priceForDay;
    @ColumnDefault(value = "1")
    @Column(name = "AVAIBLE")
    private Integer avaible;
    @ColumnDefault(value ="1")
    private  Integer activity;

}
