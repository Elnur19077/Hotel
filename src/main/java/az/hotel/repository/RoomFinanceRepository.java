package az.hotel.repository;

import az.hotel.entity.RoomsFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomFinanceRepository extends JpaRepository<RoomsFinance, Long> {
    RoomsFinance findRoomFinanceByCustomerId(Long id);
    List<RoomsFinance> findRoomsFinanceByPaymentMethodId(Long id);
}
