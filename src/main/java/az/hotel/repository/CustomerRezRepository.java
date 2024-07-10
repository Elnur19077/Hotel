package az.hotel.repository;

import az.hotel.entity.CustomerRezervInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRezRepository extends JpaRepository<CustomerRezervInfo,Long> {

     List<CustomerRezervInfo> findAllByActivity(Integer activity);
    CustomerRezervInfo findAllByIdAndActivity(Long id, Integer activity);

    }
