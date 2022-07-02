package com.barclays.store.service;

import com.barclays.store.dto.CartDTO;
import com.barclays.store.dto.CustomerDTO;
import com.barclays.store.entity.Cart;

import java.util.List;

public interface UserService {
	CustomerDTO authenticateCustomer(String emailId, String password);
//	public void logoutCustomer();
	List<Cart> getCartdetails(Integer customerId, Integer cartId);
	void addCustomer(String emailId,String name,String password,String phoneNumber);
}
