package com.example.jpa.addr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.addr.entity.Address;

public interface AddrJPARepository extends JpaRepository<Address, Long> {

}
