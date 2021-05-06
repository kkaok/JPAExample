package com.example.jpa.dept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.dept.entity.Dept;
import com.example.jpa.dept.repository.DeptJPARepository;

@Service
public class DeptService {

	 
	private DeptJPARepository deptJPARepository;
	
	DeptService(@Autowired DeptJPARepository deptJPARepository){
	    this.deptJPARepository = deptJPARepository;
	}
	
	List<Dept> findAll(){
		return deptJPARepository.findAll();
	}
	
	//@Transactional
	public void saveAll(List<Dept>  depts) {
	    deptJPARepository.saveAll(depts);
	}

	void save(Dept  dept) {
	    deptJPARepository.save(dept);
	}
	
}
