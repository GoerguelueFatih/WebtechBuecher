package de.htwberlin.webtech.Buecher.repository;

import de.htwberlin.webtech.Buecher.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUserUsername(String username);
}
