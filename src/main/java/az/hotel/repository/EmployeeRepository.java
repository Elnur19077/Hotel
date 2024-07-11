package az.hotel.repository;

import az.hotel.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   List< Employee> findAllByAvtivity(Integer activity);

   List<Employee> findAllByNameAndSurname(String name, String surname);

}
