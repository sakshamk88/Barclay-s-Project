package com.barclays.store.entity;

import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer storeId;

    private String storeName;
    private String area;
    private String pinCode;
    private String latitude;
    private String Longitude;
}
