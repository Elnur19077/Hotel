package az.hotel.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SqlQueryConstant {

    FIND_AVAILABLE_ROOMS_BY_TYPE_AND_DATE(
            "SELECT ra.rooms FROM RoomAvailability ra " +
                    "WHERE ra.rooms.roomsType = :roomsType " +
                    "AND ra.startDate <= :endDate " +
                    "AND ra.endDate >= :startDate " +
                    "AND ra.availability = 1"
    );

    private final String query;
}
