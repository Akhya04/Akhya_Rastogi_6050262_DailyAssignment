package cg.demo.associationmapping;
import java.util.Scanner;

import cg.demo.associationmapping.Employee1DAO;
import cg.demo.associationmapping.Employee1;

public class Employee1UI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Employee1DAO dao = new Employee1DAO();

        while(true)
        {
            System.out.println("\n---- MENU ----");
            System.out.println("1 Insert Employee");
            System.out.println("2 Fetch Employees with Department");
            System.out.println("3 Count Employees per Department");
            System.out.println("4 Employees by Department Name");
            System.out.println("5 Department details by Mobile");

            int ch = sc.nextInt();

            switch(ch)
            {

            case 1:

                Employee1 e = new Employee1();

                System.out.println("Enter name");
                e.setName(sc.next());

                System.out.println("Enter salary");
                e.setSalary(sc.nextDouble());

                System.out.println("Enter mobile");
                e.setMobile(sc.next());

                System.out.println("Enter Department Id");
                int deptId = sc.nextInt();

                dao.insertEmployee(e, deptId);
                break;

            case 2:
                dao.fetchEmployees();
                break;

            case 3:
                dao.countEmployeesPerDept();
                break;

            case 4:
                System.out.println("Enter Department Name");
                dao.employeesByDept(sc.next());
                break;

            case 5:
                System.out.println("Enter Mobile");
                dao.deptDetailsByMobile(sc.next());
                break;

            default:
                System.out.println("Invalid choice");
            }
        }
    }
}