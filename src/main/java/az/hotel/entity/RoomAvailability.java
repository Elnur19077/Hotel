package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Table(name = "ROOM_AVAILABILITY")
@Data
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_av_seq")
    @SequenceGenerator(name = "rooms_av_seq", sequenceName = "ROOMS_AV_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private  Rooms rooms;


    private    Integer availability;
}
