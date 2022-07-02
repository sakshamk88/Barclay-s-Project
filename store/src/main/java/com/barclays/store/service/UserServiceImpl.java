package com.barclays.store.service;

import com.barclays.store.dto.CartDTO;
import com.barclays.store.dto.CustomerDTO;
import com.barclays.store.entity.Cart;
import com.barclays.store.entity.Customer;
import com.barclays.store.exception.BarclaysException;
import com.barclays.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService
{
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CustomerDTO authenticateCustomer(String emailId, String password) {
        try {
            Optional<CustomerDTO> customerDTOOptional = customerRepository.findByEmailId(emailId.toLowerCase());
            if (customerDTOOptional != null && customerDTOOptional.get().getPassword().equals(password)) {
                return customerDTOOptional.get();
            } else {
                throw new BarclaysException("NO Customer exists with the same email-id");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public List<Cart> getCartdetails(Integer customerId, Integer cartId) {
        Customer customer= customerRepository.getReferenceById(customerId);
        Cart cart = customer.getCustomerCart();
        if(cart.getCartId()==cartId){

            List<Cart> cartList = new ArrayList<>();
            cartList = customerRepository.findByCartId(cartId);
            if(!cartList.isEmpty())
            {
                return cartList;
            }
        }
        return null;
    }
}
