package az.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "PAYMENT_METHOD")
@Data
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
    @Id
    private  Long id;
    private String method;

}
