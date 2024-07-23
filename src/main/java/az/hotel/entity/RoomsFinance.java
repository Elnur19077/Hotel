package az.hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Table(name = "ROOMS_FINANCE")
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomsFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "finance_seq")
    @SequenceGenerator(name = "finance_seq", sequenceName = "ROOM_FINANCE_SEQ", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Rooms rooms;
    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
    @Column(name = "TOTAL_PAYMENT")
    private Integer totalPayment;
    @Column(name = "REPAYMENT")
    private Integer rePayment;
    @Column(name = "DEBT")
    private Integer debt;
}

