//package cg.demo.associationmapping;
//import java.util.List;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//import cg.demo.associationmapping.Department1;
//import cg.demo.associationmapping.Employee1;
//
//public class Employee1DAO {
//
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
//    EntityManager em = emf.createEntityManager();
//
//    // 1Insert Employee
//    public void insertEmployee(Employee1 e, int deptId) {
//
//        em.getTransaction().begin();
//
//        Department1 d = em.find(Department1.class, deptId);
//
//        if(d == null)
//        {
//            System.out.println("Department not found");
//        }
//        else
//        {
//            e.setDepartment(d);
//            em.persist(e);
//            System.out.println("Employee inserted successfully");
//        }
//
//        em.getTransaction().commit();
//    }
//
//
//    // 2️Fetch all employees with department and manager
//    public void fetchEmployees() {
//
//        String jpql =
//        "SELECT e.name, e.salary, d.name, d.managerName FROM Employee1 e JOIN e.department d";
//
//        List<Object[]> list = em.createQuery(jpql).getResultList();
//
//        for(Object[] obj : list)
//        {
//            System.out.println(
//            "Employee Name: " + obj[0] +
//            " Salary: " + obj[1] +
//            " Department: " + obj[2] +
//            " Manager: " + obj[3]);
//        }
//    }
//
//
//    // 3️Count employees in each department
//    public void countEmployeesPerDept() {
//
//        String jpql =
//        "SELECT d.name, COUNT(e) FROM Department1 d LEFT JOIN d.employees e GROUP BY d.name";
//
//        List<Object[]> list = em.createQuery(jpql).getResultList();
//
//        for(Object[] obj : list)
//        {
//            System.out.println(
//            "Department: " + obj[0] +
//            " Total Employees: " + obj[1]);
//        }
//    }
//
//
//    // 4Fetch employees working under given department name
//    public void employeesByDept(String deptName) {
//
//        String jpql =
//        "SELECT e FROM Employee1 e WHERE e.department.name = :dname";
//
//        List<Employee1> list =
//        em.createQuery(jpql, Employee1.class)
//          .setParameter("dname", deptName)
//          .getResultList();
//
//        for(Employee1 e : list)
//        {
//            System.out.println(
//            "Employee Id: " + e.getId() +
//            " Name: " + e.getName() +
//            " Salary: " + e.getSalary());
//        }
//    }
//
//
//    // 5️Fetch department details using employee mobile
//    public void deptDetailsByMobile(String mobile) {
//
//        String jpql =
//        "SELECT e.id, e.name, d.name, d.managerName FROM Employee1 e JOIN e.department d WHERE e.mobile = :mob";
//
//        List<Object[]> list =
//        em.createQuery(jpql)
//          .setParameter("mob", mobile)
//          .getResultList();
//
//        for(Object[] obj : list)
//        {
//            System.out.println(
//            "Employee Id: " + obj[0] +
//            " Name: " + obj[1] +
//            " Department: " + obj[2] +
//            " Manager: " + obj[3]);
//        }
//    }
//
//}

package cg.demo.associationmapping;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// --- CRITERIA API IMPORTS ---
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class Employee1DAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    EntityManager em = emf.createEntityManager();

    // 1. Insert Employee (Remains unchanged - Criteria API is not used for inserts)
    public void insertEmployee(Employee1 e, int deptId) {
        em.getTransaction().begin();
        Department1 d = em.find(Department1.class, deptId);

        if(d == null) {
            System.out.println("Department not found");
        } else {
            e.setDepartment(d);
            em.persist(e);
            System.out.println("Employee inserted successfully");
        }
        em.getTransaction().commit();
    }


    // 2. Fetch all employees with department and manager
    public void fetchEmployees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // We expect an Object array back because we are mixing String and Double columns
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        
        Root<Employee1> root = cq.from(Employee1.class);
        
        // Perform the INNER JOIN
        Join<Object, Object> deptJoin = root.join("department");
        
        // Select specific columns using multiselect
        cq.multiselect(
            root.get("name"), 
            root.get("salary"), 
            deptJoin.get("name"), 
            deptJoin.get("managerName")
        );

        List<Object[]> list = em.createQuery(cq).getResultList();

        for(Object[] obj : list) {
            System.out.println("Employee Name: " + obj[0] + 
                               " | Salary: " + obj[1] + 
                               " | Department: " + obj[2] + 
                               " | Manager: " + obj[3]);
        }
    }


    // 3. Count employees in each department
    public void countEmployeesPerDept() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        
        // The root is Department because we are grouping by Department
        Root<Department1> root = cq.from(Department1.class);
        
        // Perform the LEFT JOIN to include departments with 0 employees
        Join<Object, Object> empJoin = root.join("employees", JoinType.LEFT);
        
        // Select the department name and the count of the joined employees
        cq.multiselect(root.get("name"), cb.count(empJoin));
        
        // Apply the GROUP BY clause
        cq.groupBy(root.get("name"));

        List<Object[]> list = em.createQuery(cq).getResultList();

        for(Object[] obj : list) {
            System.out.println("Department: " + obj[0] + 
                               " | Total Employees: " + obj[1]);
        }
    }


    // 4. Fetch employees working under given department name
    public void employeesByDept(String deptName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // We are fetching the whole Employee1 object this time
        CriteriaQuery<Employee1> cq = cb.createQuery(Employee1.class);
        
        Root<Employee1> root = cq.from(Employee1.class);
        Join<Object, Object> deptJoin = root.join("department");
        
        // Select the employee, WHERE the joined department's name equals the input
        cq.select(root).where(cb.equal(deptJoin.get("name"), deptName));

        List<Employee1> list = em.createQuery(cq).getResultList();

        for(Employee1 e : list) {
            System.out.println("Employee Id: " + e.getId() + 
                               " | Name: " + e.getName() + 
                               " | Salary: " + e.getSalary());
        }
    }


    // 5. Fetch department details using employee mobile
    public void deptDetailsByMobile(String mobile) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        
        Root<Employee1> root = cq.from(Employee1.class);
        Join<Object, Object> deptJoin = root.join("department");
        
        // Select specific columns
        cq.multiselect(
            root.get("id"), 
            root.get("name"), 
            deptJoin.get("name"), 
            deptJoin.get("managerName")
        );
        
        // Apply the WHERE condition for the mobile number
        cq.where(cb.equal(root.get("mobile"), mobile));

        List<Object[]> list = em.createQuery(cq).getResultList();

        for(Object[] obj : list) {
            System.out.println("Employee Id: " + obj[0] + 
                               " | Name: " + obj[1] + 
                               " | Department: " + obj[2] + 
                               " | Manager: " + obj[3]);
        }
    }
}