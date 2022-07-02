package com.barclays.store.service;

import com.barclays.store.dto.CartDTO;
import com.barclays.store.dto.CustomerDTO;
import com.barclays.store.entity.Cart;
import com.barclays.store.entity.Customer;
import com.barclays.store.exception.BarclaysException;
import com.barclays.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer authenticateCustomer(String emailId, String password) {
        try {
            Optional<Customer> customerDTOOptional = customerRepository.getByEmailId(emailId.toLowerCase());
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

    @Override
    public void addCustomer(String emailId, String name, String password, String phoneNumber) {
        Optional<Customer> customer = customerRepository.getByEmailId((emailId));
        try {
            if (customer.isPresent()) {
                throw new BarclaysException("Customer already Exists");
            }
            else{
                Customer customer1 = new Customer();
                customer1.setEmailId(emailId);
                customer1.setName(name);
                customer1.setPassword(password);
                customer1.setPhoneNumber(phoneNumber);
                customerRepository.save(customer1);


            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
