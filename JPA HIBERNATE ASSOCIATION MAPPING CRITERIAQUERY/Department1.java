package cg.demo.associationmapping;
import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Department1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String managerName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee1> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Set<Employee1> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee1> employees) {
        this.employees = employees;
    }
}