package az.hotel.repository;

import az.hotel.entity.RoomAvailability;
import az.hotel.entity.Rooms;
import az.hotel.enums.SqlQueryConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RoomsAvailabilityRepository extends JpaRepository<RoomAvailability,Long> {

    @Query(  "SELECT ra.rooms FROM RoomAvailability ra " +
            "WHERE ra.rooms.roomsType = :roomsType " +
            "AND ra.startDate <= :endDate " +
            "AND ra.endDate >= :startDate " +
            "AND ra.availability = 1")
    List<Rooms> findAvailableRoomsByTypeAndDate(
            @Param("roomsType") String roomsType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
