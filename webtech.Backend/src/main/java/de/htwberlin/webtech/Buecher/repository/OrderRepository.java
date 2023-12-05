/*package de.htwberlin.webtech.Buecher.repository;

import de.htwberlin.webtech.Buecher.model.BookOrder;
import de.htwberlin.webtech.Buecher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findByUserUsername(String username);
    List<BookOrder> getOrdersForUser(String username);
    BookOrder placeOrder(BookOrder bookOrder, String username);

}

*/