package zw.co.jugaad.data.repositories;


import org.springframework.stereotype.Component;
import zw.co.jugaad.data.entities.CousineData;
import zw.co.jugaad.data.entities.StoreData;
import zw.co.jugaad.domain.Cousine;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.cousine.CousineRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CousineRepositoryImpl implements CousineRepository {

    private JpaCousineRepository jpaCousineRepository;

    public CousineRepositoryImpl(JpaCousineRepository jpaCousineRepository) {
        this.jpaCousineRepository = jpaCousineRepository;
    }

    @Override
    public List<Store> getStoresById(Identity id) {
        return jpaCousineRepository
                .findStoresById(id.getNumber())
                .parallelStream()
                .map(StoreData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cousine> getAll() {
        return jpaCousineRepository
                .findAll()
                .parallelStream()
                .map(CousineData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cousine> searchByName(String search) {
        return jpaCousineRepository
                .findByNameContainingIgnoreCase(search)
                .parallelStream()
                .map(CousineData::fromThis)
                .collect(Collectors.toList());
    }
}
