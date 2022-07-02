package com.barclays.store.controller;


import com.barclays.store.dto.CartDTO;
import com.barclays.store.dto.CustomerDTO;
import com.barclays.store.entity.Cart;
import com.barclays.store.exception.BarclaysException;
import com.barclays.store.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/user-controller")
@Validated
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping(value="/authenticateUser/{emailId}/{password}")
    public ResponseEntity<CustomerDTO> authenticateCustomer(@PathVariable("emailId") String emailId,@PathVariable("password") String password) throws BarclaysException {
        CustomerDTO customerDTO=userService.authenticateCustomer(emailId,password);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(value="/getCartDetails/{customerId}/{cartId}")
    public ResponseEntity<List<Cart>> getCartDetails(@PathVariable("customerId") Integer customerId, @PathVariable("cartId") Integer cartIdd) throws BarclaysException {
        List<Cart> cartList=userService.getCartdetails(customerId,cartIdd);

        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping(value="/addUser")
    public ResponseEntity<String> addUser(@RequestBody  @Validated CustomerDTO customerDTO) throws BarclaysException, NoSuchAlgorithmException {
        userService.addCustomer(customerDTO.getEmailId(),customerDTO.getName(), customerDTO.getPassword(), customerDTO.getPhoneNumber());

        return new ResponseEntity<>("Successfully Inserted", HttpStatus.OK);
    }
}
