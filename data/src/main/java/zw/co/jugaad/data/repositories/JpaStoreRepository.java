package zw.co.jugaad.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.jugaad.data.entities.ProductData;
import zw.co.jugaad.data.entities.StoreData;

import java.util.List;

public interface JpaStoreRepository extends JpaRepository<StoreData, Long> {
    List<StoreData> findByNameContainingIgnoreCase(String name);

    @Query("select p from store s join s.products p where s.id = ?1")
    List<ProductData> findProductsById(Long id);
}
