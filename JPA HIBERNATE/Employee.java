package cg.demo.jpahibernate.entities;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double salary;
    private String dept;

    @ElementCollection
    private List<Long> phoneno;

    public Employee() {
    }

    public Employee(String dept, String name, List<Long> phoneno, double salary) {
        this.dept = dept;
        this.name = name;
        this.phoneno = phoneno;
        this.salary = salary;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public String getDept() { return dept; }

    public void setDept(String dept) { this.dept = dept; }

    public List<Long> getPhoneno() { return phoneno; }

    public void setPhoneno(List<Long> phoneno) { this.phoneno = phoneno; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary +
                ", dept=" + dept + ", phoneno=" + phoneno + "]";
    }
}