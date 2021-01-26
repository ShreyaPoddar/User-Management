package com.paytm.inpg.transaction.repository;

import com.paytm.inpg.transaction.entity.TransactionElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionRepositoryElastic extends ElasticsearchRepository<TransactionElastic,Integer> {
//    List<TransactionElastic> findByPayerphonenumber(String payerphonenumber);
//    List<TransactionElastic> findByPayeephonenumber(String payeephonenumber);
}
