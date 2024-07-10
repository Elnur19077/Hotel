package az.hotel.repository;

import az.hotel.entity.Customer;
import az.hotel.entity.CustomerAdditionalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerAddRepository extends CrudRepository<CustomerAdditionalInfo, Long> {
 CustomerAdditionalInfo findByIdAndActivity(Long id,Integer activity);

 List<CustomerAdditionalInfo> findAllByActivity(Integer activity);
List<CustomerAdditionalInfo> findCustomerAdditionalInfoByCustomerName(String name);

}
