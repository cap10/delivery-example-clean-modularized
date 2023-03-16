package zw.co.jugaad.usecases.order;





import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Order;

import java.beans.JavaBean;
import java.util.Optional;

@JavaBean
public interface OrderRepository {

    Order persist(Order order);

    Optional<Order> getById(Identity id);
}
