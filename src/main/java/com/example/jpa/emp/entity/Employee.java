package com.example.jpa.emp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.example.jpa.dept.entity.Dept;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name="TBL_EMPLOYEES")
@Getter
@Setter
@EqualsAndHashCode(exclude = "dept")
public class Employee implements Serializable{
	
    private static final long serialVersionUID = 1;
    
    @Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String email;
	private String stat;
	private LocalDateTime created;
	private LocalDateTime updated;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    //@ManyToOne
    //@JoinColumn(name = "dept_id", nullable=false, insertable=false, updatable=false)
    private Dept dept;
    
    @Override
    public String toString() {
        return ToStringBuilder
            .reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.stat = "Y";
        this.created =  LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public Employee() {}
    

}

