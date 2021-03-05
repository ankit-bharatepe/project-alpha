package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.MerchantExpensesAggregator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MerchantExpensesAggregatorDao extends CrudRepository<MerchantExpensesAggregator, Long> {

    List<MerchantExpensesAggregator> findByStatusAndMerchantId(String status, String merchantId);
    List<MerchantExpensesAggregator> findByStatusAndBillDate(String status, Date billDate);
}
