package com.barclays.store.repository;

import com.barclays.store.dto.CustomerDTO;
import com.barclays.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.barclays.store.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //@Query(value="Select * from customer where email_id=:emailId",nativeQuery = true)
    public Optional<Customer> getByEmailId(String emailId);

    @Query(value = "Select * from cart where cart_id=:cartId",nativeQuery = true)
    public List<Cart> findByCartId(Integer cartId);


}
