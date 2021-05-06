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
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

    }

    public Dept() {}

    @Column(name="dept_cd")
    private String deptCd;

    @Column(name="dept_name")
    private String deptName;

    private String stat;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

    public void addEmployee(Employee employee) {
        employee.setDept(this);
        this.employees.add(employee);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deptCd == null) ? 0 : deptCd.hashCode());
        result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Dept other = (Dept) obj;
        if (deptCd == null) {
            if (other.deptCd != null)
                return false;
        } else if (!deptCd.equals(other.deptCd))
            return false;
        if (deptName == null) {
            if (other.deptName != null)
                return false;
        } else if (!deptName.equals(other.deptName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stat == null) {
            if (other.stat != null)
                return false;
        } else if (!stat.equals(other.stat))
            return false;
        return true;
    }

}
