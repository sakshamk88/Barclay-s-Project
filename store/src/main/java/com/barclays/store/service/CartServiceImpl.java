package com.barclays.store.service;

import com.barclays.store.entity.Cart;
import com.barclays.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartRepository cartRepository;

	@Override
	public void addProduct(Integer productId, Integer cartId, Integer quantity) {
		List<Cart> cartList = cartRepository.findByCartId(cartId);
		if(!cartList.isEmpty())
		{
			for (Cart cart:cartList
				 ) {
				if(cart.getProduct().getProductId()==productId)
				{
					cart.getProduct().setAvailableQuantity(cart.getProduct().getProductId()+quantity);
				}

			}
			cartRepository.saveAll(cartList);
		}
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void deleteProduct(Integer productId, Integer cartId) {
		List<Cart> cartList = cartRepository.findByCartId(cartId);
		List<Cart> cartsToBeDeleted = new ArrayList<>();
		if(!cartList.isEmpty())
		{
			for (Cart cart:cartList
			) {
				if(cart.getProduct().getProductId()==productId)
				{
					cartsToBeDeleted.add(cart);
				}

			}
			cartRepository.deleteAll(cartsToBeDeleted);
		}
		
	}

	@Override
	public void updateProduct(Integer productId, Integer cartId, Integer quantity) {
		List<Cart> cartList = cartRepository.findByCartId(cartId);
		if(!cartList.isEmpty())
		{
			for (Cart cart:cartList
			) {
				if(cart.getProduct().getProductId()==productId)
				{
					cart.getProduct().setAvailableQuantity(quantity);
				}

			}
			cartRepository.saveAll(cartList);
		}
		
	}

}
