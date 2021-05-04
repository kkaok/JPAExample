package com.example.jpa.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.repository.DeptJPARepository;

@Service
public class DeptService {

	@Autowired 
	private DeptJPARepository deptJPARepository;
	
	public List<Dept> findAll(){
		return deptJPARepository.findAll();
	}
	
	//@Transactional
	public void saveAll(List<Dept>  depts) {
	    deptJPARepository.saveAll(depts);
	}

	public void save(Dept  dept) {
	    deptJPARepository.save(dept);
	}
	
}
