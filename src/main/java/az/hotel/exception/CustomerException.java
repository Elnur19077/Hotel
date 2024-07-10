package az.hotel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor


public class CustomerException extends RuntimeException{
    private Integer code;

    public CustomerException(String message, Integer code) {
        super(message);
        this.code = code;

    }

    public  Integer getErrorCode() {
        return code;
    }



}
