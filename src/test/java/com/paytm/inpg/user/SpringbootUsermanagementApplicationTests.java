package com.paytm.inpg.user;

import com.paytm.inpg.transaction.entity.Transaction;
import com.paytm.inpg.transaction.repository.TransactionRepository;
import com.paytm.inpg.transaction.service.TransactionService;
import com.paytm.inpg.wallet.entity.Wallet;
import com.paytm.inpg.wallet.repository.WalletRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootUsermanagementApplicationTests {

	@Autowired
	private TransactionService service;

	@MockBean
	private TransactionRepository transactionRepository;

	@MockBean
	private WalletRepository walletRepository;

	//Transaction Service test case
	@Test
	public void findByPhonenumberTest()
	{
		String phone="900";
		when(walletRepository.findByPhonenumber(phone)).thenReturn(Stream.of(new Wallet(40,"900",8000.0)).collect(Collectors.toList()));

		assertEquals(1,service.findByPhonenumber(phone).size());
	}
	//Transaction Service test case
	@Test
	public void saveTransactionTest()
	{
		Transaction transaction=new Transaction(45,"200","300",500.0);
		when(transactionRepository.save(transaction)).thenReturn(transaction);
		assertEquals(transaction,service.saveTransaction(transaction));
	}


}
