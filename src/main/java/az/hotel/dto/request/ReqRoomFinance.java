package az.hotel.dto.request;

import lombok.Data;

@Data
public class ReqRoomFinance {
    private  Long id;
    private Long roomsId;
    private Long customerId;
    private Long paymentMethodId;
    private Integer totalPayment;
    private Integer rePayment;
    private Integer debt;
}
