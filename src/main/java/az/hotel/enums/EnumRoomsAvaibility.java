package az.hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumRoomsAvaibility {

    IS_AVAIBLE(1) , NO_AVABILITY(0);



    private int value;
}
