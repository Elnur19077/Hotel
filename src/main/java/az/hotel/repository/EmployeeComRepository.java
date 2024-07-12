package az.hotel.repository;

import az.hotel.entity.EmployeeCommunication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeComRepository extends JpaRepository<EmployeeCommunication,Long > {
    EmployeeCommunication findByIdAndActivity(Long id,Integer activity);

    List<EmployeeCommunication> findAllByActivity(Integer activity);
}
