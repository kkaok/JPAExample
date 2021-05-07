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

    private static final long serialVersionUID = 8697564380603342586L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String stat;
    private Date created;
    private Date updated;

    EmpAPI(){
        super();
    }
	

}
