package com.example.jpa.dept.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.jpa.emp.entity.Employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name="TBL_DEPT")
@Getter
@Setter
@ToString(exclude = "employees")
public class Dept implements Serializable{

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;
    
    public Dept(String deptCd, String deptName) {
        this.deptCd = deptCd;
        this.deptName = deptName;
        this.stat = "Y";
        this.created = new Date();
        this.updated = new Date();
    }

    public Dept() {}

    @Column(name="dept_cd")
    private String deptCd;

    @Column(name="dept_name")
    private String deptName;

    private String stat;
    private Date created;
    private Date updated;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    public void addEmployee(Employee employee) {
        employee.setDept(this);
        this.employees.add(employee);
    }

}
