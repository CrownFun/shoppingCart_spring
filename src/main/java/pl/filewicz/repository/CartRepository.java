package pl.filewicz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.filewicz.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


}
