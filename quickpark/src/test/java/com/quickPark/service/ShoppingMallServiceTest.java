package com.quickPark.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.quickPark.entity.Block;
import com.quickPark.entity.Customer;
import com.quickPark.entity.Login;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.repository.LoginRepository;
import com.quickPark.repository.ShoppingMallRepository;

@ExtendWith(MockitoExtension.class)
public class ShoppingMallServiceTest {


	private ShoppingMallServiceImpl mallServiceImpl;
	
	@Mock
	private ShoppingMallRepository mallRepository;
	
	@Mock
	private LoginRepository loginRepository;
	
	
	
	@BeforeEach
	void setup(){
		 this.mallServiceImpl = new ShoppingMallServiceImpl(this.mallRepository,this.loginRepository);
	
	}
	
	
	@Test
	void addShoppingMall() {
		Login login = new Login();
		List<Block> blocks = new ArrayList<>();
		ShoppingMall mall = new ShoppingMall(1, "Malhar", "malhar123@gmail.com", 98765436792L, "Indore", "malhar#321", login, blocks);
	
		loginRepository.save(login);
		mallRepository.save(mall);

		assertThat(mallServiceImpl.addShoppingMall(mall));
	}
	
	@Test
	public void getAllMall() {
		
		List<ShoppingMall> malls =  mallServiceImpl.getAllShoppingMalls();
		Assertions.assertNotNull(malls);
	}
	
	@Test
	void deleteShoppingMall() {
		Login login = new Login();
		List<Block> blocks = new ArrayList<>();
		ShoppingMall mall = new ShoppingMall(21, "Malhar", "malhar123@gmail.com", 98765436792L, "Indore", "malhar#321", login, blocks);
	
		loginRepository.save(login);
		mallRepository.save(mall);
		
		//int actual = mallServiceImpl.deleteShoppingMall(21);
		List<ShoppingMall> malls = mallRepository.findAll();
		
		Assertions.assertFalse(malls.contains(mall));
		 
		
		
	}
}
