package com.bharatpe.projectalpha.projectalpha.dao;

import com.bharatpe.projectalpha.projectalpha.entity.ActiveCashBack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveCashBackDao extends CrudRepository<ActiveCashBack , Long> {

    List<ActiveCashBack> findByMerchantIdAndStatus(String merchantId , String status);
}
