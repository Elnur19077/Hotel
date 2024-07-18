package az.hotel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RoomsExpection  extends  RuntimeException{
    private int code;

    public RoomsExpection (String message, Integer code) {
        super(message);
        this.code = code;

    }
    public  Integer getErrorCode() {
        return code;
    }
}
