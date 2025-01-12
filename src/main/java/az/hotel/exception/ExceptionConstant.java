package az.hotel.exception;

import lombok.Data;

@Data
public final class ExceptionConstant {
    public static final Integer INTERNAL_EXCEPTION = 100;

    public static final Integer INVALID_REQUEST_DATA = 101;

    public static final Integer CUSTOMER_NOT_FOUND = 102;
    public static final Integer NO_OTEL_INFO = 103;
    public static final Integer EMPLOYEE_NOT_FOUND = 104;
    public static final Integer NO_ROOMS_DETAILS = 105;
    public static final Integer ROOM_FINANCE_NOT_FOUND = 106;
}
