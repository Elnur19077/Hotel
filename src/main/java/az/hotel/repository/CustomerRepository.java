package az.hotel.repository;


import az.hotel.entity.Customer;
import az.hotel.entity.CustomerAdditionalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> getCustomersByActivity(Integer activity);

}
