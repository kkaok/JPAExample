package com.example.jpa.emp.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpAPI implements Serializable{

    private static final long serialVersionUID = 1L;

    EmpAPI(){
        super();
    }
	
    private Long id;
	
    private String deptCd;

	private String firstName;

	private String lastName;

	private String email;
	private String stat;
	private Date created;
	private Date updated;

}
