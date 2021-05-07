package com.example.jpa.dept.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="TBL_DEPT")
@Getter
@Setter
@ToString(exclude = "employees")
@EqualsAndHashCode(exclude = "employees")
@NoArgsConstructor 
public class Dept implements Serializable{

    private static final long serialVersionUID = 1;
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;
    
    @Column(name="dept_cd")
    private String deptCd;

    @Column(name="dept_name")
    private String deptName;

    private String stat;
    
    private LocalDateTime created;
    
    private LocalDateTime updated;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    @Builder
    public Dept(String deptCd, String deptName) {
        this.deptCd = deptCd;
        this.deptName = deptName;
        this.stat = "Y";
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

    }

    public void addEmployee(Employee employee) {
        employee.setDept(this);
        this.employees.add(employee);
    }

}
