package az.hotel.dto.request;

import az.hotel.dto.response.CustomerResp;
import az.hotel.dto.response.PaymentMethodResp;
import az.hotel.dto.response.RoomsResp;
import lombok.Data;

@Data
public class ReqPaymentMethod {
    private Long roomsId;
    private Long customerId;
    private Long paymentMethodId;
    private Integer totalPayment;
    private Integer rePayment;
    private Integer debt;
}
