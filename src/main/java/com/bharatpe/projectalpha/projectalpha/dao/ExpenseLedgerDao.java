package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseLedgerDao extends CrudRepository<ExpenseLedger, Long> {

    List<ExpenseLedger> findByMerchantId(String merchantId);

    List<ExpenseLedger> findByMerchantIdAndMerchantExpenseAggId(String merchantId, String merchantExpenseAggId);

    List<ExpenseLedger> findByStatusOrderByAmountAsc(String status);

}
