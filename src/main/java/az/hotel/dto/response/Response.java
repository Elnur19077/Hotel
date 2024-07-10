package az.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class Response<T>{

    @JsonProperty(value = "response")
 private  T t;
    private  RespStatus status;

}
