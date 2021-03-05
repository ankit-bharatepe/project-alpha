package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.DummyTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DummyTransactionDao extends CrudRepository<DummyTransaction,Long> {

    @Query(nativeQuery = true,value = "select * from dummy_transaction")
    List<DummyTransaction> getAllTransaction();

    @Query(value = "select * from dummy_transaction order by id desc limit 3",nativeQuery = true)
    List<DummyTransaction> getIsLucky();
}
