package az.hotel.dto.response;

import az.hotel.entity.Customer;
import az.hotel.entity.PaymentMethod;
import az.hotel.entity.Rooms;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class RoomFinanceResp {
    private Long id;
    private  RoomsResp roomsResp;
    private  CustomerResp customerResp;
    private  PaymentMethodResp paymentMethodResp;
    private Integer totalPayment;
    private Integer rePayment;
    private Integer debt;
}
