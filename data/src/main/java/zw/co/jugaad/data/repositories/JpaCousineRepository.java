package zw.co.jugaad.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.jugaad.data.entities.CousineData;
import zw.co.jugaad.data.entities.StoreData;

import java.util.List;

public interface JpaCousineRepository extends JpaRepository<CousineData, Long> {
    List<CousineData> findByNameContainingIgnoreCase(String search);

    @Query("select s from cousine c join c.stores s where c.id = ?1")
    List<StoreData> findStoresById(Long id);
}
