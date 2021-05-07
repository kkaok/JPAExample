package com.example.jpa.addr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="TBL_ADDR")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor 
public class Address implements Serializable{
    
    private static final long serialVersionUID = 1;
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
  
    private String address;

    @Builder
    public Address(String address){
        this.address = address;
    }

}
