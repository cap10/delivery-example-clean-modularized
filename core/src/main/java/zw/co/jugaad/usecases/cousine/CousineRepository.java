package zw.co.jugaad.usecases.cousine;





import zw.co.jugaad.domain.Cousine;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Store;

import java.util.List;

public interface CousineRepository {
    List<Store> getStoresById(Identity id);

    List<Cousine> getAll();

    List<Cousine> searchByName(String search);
}
