package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseLedgerDao extends CrudRepository<ExpenseLedger, Long> {

    @Query(nativeQuery = true , value = "select * from expense_ledger where merchant_id=:merchantId")
    List<ExpenseLedger> findBYMerchantId(String merchantId);

    List<ExpenseLedger> findByMerchantIdAndMerchantExpenseAggId(String merchantId, Long merchantExpenseAggId);
}
