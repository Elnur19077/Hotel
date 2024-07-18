package az.hotel.repository;

import az.hotel.entity.RoomAvailabity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsAvailabityRepository extends JpaRepository<RoomAvailabity,Long> {
    List<RoomAvailabity> findByCustomersId(Long customerId);
}
