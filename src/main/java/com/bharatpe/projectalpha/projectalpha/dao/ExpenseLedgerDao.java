package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.ExpenseLedger;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseLedgerDao extends CrudRepository<ExpenseLedger, Long> {

    List<ExpenseLedger> findBYMerchantId(Long merchantId);

    List<ExpenseLedger> findByMerchantIdAndMerchantExpenseAggId(Long merchantId, Long merchantExpenseAggId);
}
