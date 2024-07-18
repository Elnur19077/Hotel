package az.hotel.repository;

import az.hotel.entity.Rooms;
import az.hotel.enums.EnumRoomsAvaibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    List<Rooms> findRoomsByRoomsTypeAndAvaible(String roomsType, Integer empty);

    List<Rooms> findRoomsByPriceForDayAndAvaible(Integer price, Integer avaibility);
    Rooms findRoomsByIdAndAvaible(Long id, Integer value);

    List<Rooms> findRoomsByNumberOfBedAndAvaible(Integer bed, Integer avaibility);
   List<Rooms>  findRoomsByAvaible(Integer avaibility);

}

