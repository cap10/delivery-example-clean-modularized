package zw.co.jugaad.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.jugaad.data.entities.ProductData;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductData, Long> {
    List<ProductData> findByNameContainingOrDescriptionContainingAllIgnoreCase(String name, String description);

    List<ProductData> findByStoreIdAndIdIsIn(Long id, List<Long> ids);
}
