package com.barclays.store.controller;

import com.barclays.store.entity.Customer;
import com.barclays.store.exception.BarclaysException;
import com.barclays.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/user-controller")
@Validated
public class StoreController
{
    @Autowired
    StoreService storeService;
    @GetMapping(value="/getStores")
    public ResponseEntity<Boolean> getStores() throws BarclaysException {
        Boolean res=storeService.getEmployees();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value="/getStoreName/{lat}/{lon}")
    public ResponseEntity<String> getNearestStore(@PathVariable("lat") String lat,@PathVariable("lon") String lon) throws BarclaysException {
        String storeName=storeService.getMinimumDistance(Double.parseDouble(lat),Double.parseDouble(lon));
        return new ResponseEntity<>(storeName, HttpStatus.OK);
    }

}
