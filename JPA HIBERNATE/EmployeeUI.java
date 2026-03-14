package cg.demo.jpahibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cg.demo.jpahibernate.entities.Employee;

public class EmployeeUI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        System.out.println("Starting App!");

        while (true) {

            System.out.println("\n------ EMPLOYEE MENU ------");
            System.out.println("1 Insert Employee");
            System.out.println("2 Get Employee By Id");
            System.out.println("3 Update Salary");
            System.out.println("4 Delete Employee");
            System.out.println("5 Get Employee By Name");
            System.out.println("6 Find All Employees");
            System.out.println("7 Count Employees By Department");
            System.out.println("8 Find Employees By Salary");
            System.out.println("9 Find Employee By Phone Number");
            System.out.println("10 Find with salary gretaer than 5000");
            System.out.println("11 Exit");

            System.out.print("Enter choice : ");
            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                System.out.print("Enter dept : ");
                String dept = sc.next();

                System.out.print("Enter name : ");
                String name = sc.next();

                System.out.print("Enter phone : ");
                long phone = sc.nextLong();

                System.out.print("Enter salary : ");
                double sal = sc.nextDouble();

                List<Long> phones = new ArrayList<>();
                phones.add(phone);

                Employee emp = new Employee(dept, name, phones, sal);

                dao.insertEmployee(emp);

                System.out.println("Employee inserted successfully");

                break;

            case 2:

                System.out.print("Enter id : ");
                int eid = sc.nextInt();

                Employee e = dao.getEmployeeById(eid);

                if (e != null)
                    System.out.println(e);
                else
                    System.out.println("Employee not found");

                break;

            case 3:

                System.out.print("Enter id : ");
                int uid = sc.nextInt();

                System.out.print("Enter new salary : ");
                double newsal = sc.nextDouble();

                Employee updated = dao.updateSalary(uid, newsal);

                if (updated != null)
                    System.out.println("Salary updated to: " + updated.getSalary());
                else
                    System.out.println("Employee not found");

                break;

            case 4:

                System.out.print("Enter id : ");
                int did = sc.nextInt();

                Employee deleted = dao.deleteEmployee(did);

                if (deleted != null)
                    System.out.println("Employee deleted successfully");
                else
                    System.out.println("Employee not found");

                break;

            case 5:

                System.out.print("Enter name : ");
                String name1 = sc.next();

                List<Employee> list = dao.getEmployeeByName(name1);

                if(list.isEmpty())
                    System.out.println("Employee not found");
                else
                    list.forEach(System.out::println);

                break;
            case 6:

                dao.findEmployee().forEach(System.out::println);

                break;

            case 7:

                dao.countEmployeesByDept().forEach(row ->
                        System.out.println("Dept: " + row[0] + "  Count: " + row[1]));

                break;

            case 8:

                System.out.print("Enter salary : ");
                double s = sc.nextDouble();

                dao.findBySalary(s).forEach(System.out::println);

                break;

            case 9:

                System.out.print("Enter phone number : ");
                long ph = sc.nextLong();

                List<Employee> phoneList = dao.findByMobile(ph);

                if(phoneList.isEmpty())
                    System.out.println("Employee not found with this phone number");
                else
                    phoneList.forEach(System.out::println);

                break;
            case 10:

                List<Employee> salaryList = dao.findSalary1();

                for(Employee e2 : salaryList)
                {
                    System.out.println(e2);
                }

                break;
            	

            case 11:

                System.out.println("Program ended");
                sc.close();
                System.exit(0);

            default:
                System.out.println("Invalid choice");
            }
        }
    }
}