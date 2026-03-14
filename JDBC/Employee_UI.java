package com.demo.jdbc;

import java.util.*;

public class Employee_UI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT DATABASE MENU =====");
            System.out.println("1. Insert Student");
            System.out.println("2. View All Students");
            System.out.println("3. Fetch Student By ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            try {

                switch (ch) {

                    // INSERT
                    case 1:

                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        S1 s = new S1(name, age, course);

                        S1 inserted = Employee_DAO.insertStudent(s);

                        System.out.println("Inserted Student ID: " + inserted.student_id);

                        break;

                    // VIEW ALL
                    case 2:

                        List<S1> list = Employee_DAO.fetchStudents();

                        System.out.println("\nID | Name | Age | Course");

                        for (S1 st : list) {

                            System.out.println(
                                    st.student_id + " | " +
                                    st.name + " | " +
                                    st.age + " | " +
                                    st.course
                            );
                        }

                        break;

                    // FETCH BY ID
                    case 3:

                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();

                        S1 student = Employee_DAO.fetchStudentById(id);

                        if (student != null) {

                            System.out.println(
                                    student.student_id + " | " +
                                    student.name + " | " +
                                    student.age + " | " +
                                    student.course
                            );
                        } else {
                            System.out.println("Student Not Found");
                        }

                        break;

                    // UPDATE
                    case 4:

                        System.out.print("Enter Student ID: ");
                        int uid = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String uname = sc.nextLine();

                        System.out.print("Enter New Age: ");
                        int uage = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter New Course: ");
                        String ucourse = sc.nextLine();

                        S1 us = new S1(uid, uname, uage, ucourse);

                        S1 updated = Employee_DAO.updateStudent(us);

                        if (updated != null)
                            System.out.println("Student Updated Successfully");
                        else
                            System.out.println("Student Not Found");

                        break;

                    // DELETE
                    case 5:

                        System.out.print("Enter Student ID: ");
                        int did = sc.nextInt();

                        S1 deleted = Employee_DAO.deleteStudent(did);

                        if (deleted != null)
                            System.out.println("Deleted Student: " + deleted.name);
                        else
                            System.out.println("Student Not Found");

                        break;

                    case 6:

                        System.out.println("Program Ended");
                        return;

                    default:

                        System.out.println("Invalid Choice");
                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }
            
        }
    }
}