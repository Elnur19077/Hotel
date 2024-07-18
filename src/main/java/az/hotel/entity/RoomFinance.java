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
public class RoomFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "rooms_id", nullable = false)
    private Rooms rooms;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
    @Column(name = "TOTAL_PAYMENT")
    private Integer totalPayment;
    @Column(name = "REPAYMENT")
    private Integer rePayment;
    private Integer debt;
}
