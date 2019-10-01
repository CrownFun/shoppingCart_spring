package pl.filewicz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.filewicz.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
