package com.example.jpa.emp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.BatchSize;

import com.example.jpa.dept.entity.Dept;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="TBL_EMPLOYEES")
@Getter
@Setter
@ToString
public class Employee implements Serializable{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.stat = "Y";
		this.created = new Date();
		this.updated = new Date();
	}

	public Employee(Long deptId, String firstName, String lastName, String email) {
	    //this.deptId = deptId;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	    this.stat = "Y";
	    this.created = new Date();
	    this.updated = new Date();
	}

//    @Column(name="dept_id")
//    private Long deptId;

    @Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String email;
	private String stat;
	private Date created;
	private Date updated;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    //@ManyToOne
    //@JoinColumn(name = "dept_id")
    //@JoinColumn(name = "dept_id", nullable=false, insertable=false, updatable=false)
    //@JoinColumn(name = "dept_id")
    private Dept dept;
    
    @Override
    public String toString() {
        return ToStringBuilder
            .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}