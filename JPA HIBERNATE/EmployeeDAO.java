package cg.demo.jpahibernate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.TypedQuery;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import cg.demo.jpahibernate.entities.Employee;

public class EmployeeDAO {

    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("JPA-PU");

    EntityManager em = emf.createEntityManager();

    // INSERT
    public Employee insertEmployee(Employee e) {

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();

        return e;
    }

    // FETCH BY ID
    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }

    
    // UPDATE SALARY
    public Employee updateSalary(int id, double newsal) {

        Employee e = em.find(Employee.class, id);

        if (e != null) {
            em.getTransaction().begin();
            e.setSalary(newsal);
            em.getTransaction().commit();
        }

        return e;
    }

    // DELETE
    public Employee deleteEmployee(int id) {

        Employee e = em.find(Employee.class, id);

        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }

        return e;
    }
//FETCH BY NAME
    public List<Employee> getEmployeeByName(String name) {

        TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e WHERE e.name = :name",
                Employee.class);

        query.setParameter("name", name);

        return query.getResultList();
    }
    // FIND ALL EMPLOYEES
//    public List<Employee> findEmployee() {
//
//        TypedQuery<Employee> tQuery =
//                em.createQuery("SELECT e FROM Employee e", Employee.class);
//
//        return tQuery.getResultList();
//    }
    
    public List<Employee> findEmployee() {
    	CriteriaBuilder cb = em.getCriteriaBuilder();

    	CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

    	Root<Employee> root = cq.from(Employee.class);

    	cq.select(root);

    	TypedQuery<Employee> query = em.createQuery(cq);

    	List<Employee> employees = query.getResultList();

    	return employees;
    }

    // Number of employees in each department
    public List<Object[]> countEmployeesByDept() {

        TypedQuery<Object[]> q = em.createQuery(
            "SELECT e.dept, COUNT(e) FROM Employee e GROUP BY e.dept",
            Object[].class
        );

        return q.getResultList();
    }

    // Find employees with specific salary
    public List<Employee> findBySalary(double salary) {

        TypedQuery<Employee> q = em.createQuery(
                "SELECT e FROM Employee e WHERE e.salary = :sal",
                Employee.class);

        q.setParameter("sal", salary);

        return q.getResultList();
    }
//Find employees with salary greater than 5000
    public List<Employee> findSalary1() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> root = cq.from(Employee.class);

        cq.select(root)
          .where(cb.gt(root.get("salary"), 5000));

        return em.createQuery(cq).getResultList();
    }
    
    // Find employee using mobile number
    public List<Employee> findByMobile(long phone){

        TypedQuery<Employee> q = em.createQuery(
            "SELECT e FROM Employee e JOIN e.phoneno p WHERE p = :ph",
            Employee.class);

        q.setParameter("ph", phone);

        return q.getResultList();
    }

}