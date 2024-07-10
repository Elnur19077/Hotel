package az.hotel.repository;

import az.hotel.entity.OtelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
@Repository
public interface OtelRepository extends JpaRepository<OtelInfo, Long> {


}
