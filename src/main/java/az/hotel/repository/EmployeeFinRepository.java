package az.hotel.repository;

import az.hotel.entity.EmployeeFinInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeFinRepository extends JpaRepository<EmployeeFinInfo,Long> {
    EmployeeFinInfo findByIdAndActivity(Long id, Integer activity);

    List<EmployeeFinInfo> findAllByActivity(Integer activity);
}
