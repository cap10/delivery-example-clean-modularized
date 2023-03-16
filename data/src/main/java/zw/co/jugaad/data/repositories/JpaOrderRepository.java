package zw.co.jugaad.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.jugaad.data.entities.OrderData;


public interface JpaOrderRepository extends JpaRepository<OrderData, Long> {
}
