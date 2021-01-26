package com.paytm.inpg.transaction.repository;

import com.paytm.inpg.transaction.entity.TransactionElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepositoryElastic extends ElasticsearchRepository<TransactionElastic,Integer> {
    List<TransactionElastic> findByPayerphonenumber(String payerphonenumber);
    List<TransactionElastic> findByPayeephonenumber(String payeephonenumber);
}
