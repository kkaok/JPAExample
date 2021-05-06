package com.example.jpa.emp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
		this.created =  LocalDateTime.now();
		this.updated = LocalDateTime.now();
	}

//	public Employee(Long deptId, String firstName, String lastName, String email) {
//	    //this.deptId = deptId;
//	    this.firstName = firstName;
//	    this.lastName = lastName;
//	    this.email = email;
//	    this.stat = "Y";
//	    this.created = new Date();
//	    this.updated = new Date();
//	}

//    @Column(name="dept_id")
//    private Long deptId;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dept == null) ? 0 : dept.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((stat == null) ? 0 : stat.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (dept == null) {
            if (other.dept != null)
                return false;
        } else if (!dept.equals(other.dept))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (stat == null) {
            if (other.stat != null)
                return false;
        } else if (!stat.equals(other.stat))
            return false;
        return true;
    }

    
}