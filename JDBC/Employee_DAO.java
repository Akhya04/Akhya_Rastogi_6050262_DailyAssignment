package com.demo.jdbc;


import java.sql.*;
import java.util.*;

public class Employee_DAO {

    static final String URL =
    "jdbc:postgresql://ep-lingering-waterfall-ahk3pu5w-pooler.c-3.us-east-1.aws.neon.tech/neondb?sslmode=require";

    static final String USER = "neondb_owner";
    static final String PASSWORD = "npg_NUa9e3wlkQFc";

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // INSERT
    public static S1 insertStudent(S1 s) throws Exception {

        Connection conn = getConnection();

        String sql = "INSERT INTO student(student_name, age, course) VALUES (?, ?, ?) RETURNING student_id";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, s.name);
        pst.setInt(2, s.age);
        pst.setString(3, s.course);

        ResultSet rs = pst.executeQuery();

        if(rs.next()) {
            s.student_id = rs.getInt("student_id");
        }

        conn.close();

        return s;
    }

    // FETCH ALL
    public static List<S1> fetchStudents() throws Exception {

        Connection conn = getConnection();

        List<S1> list = new ArrayList<>();

        String sql = "SELECT * FROM student";

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {

            S1 s = new S1(
                rs.getInt("student_id"),
                rs.getString("student_name"),
                rs.getInt("age"),
                rs.getString("course")
            );

            list.add(s);
        }

        conn.close();

        return list;
    }

    // FETCH BY ID
    public static S1 fetchStudentById(int id) throws Exception {

        Connection conn = getConnection();

        String sql = "SELECT * FROM student WHERE student_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        S1 s = null;

        if(rs.next()) {

            s = new S1(
                rs.getInt("student_id"),
                rs.getString("student_name"),
                rs.getInt("age"),
                rs.getString("course")
            );
        }

        conn.close();

        return s;
    }

    // UPDATE
    public static S1 updateStudent(S1 s) throws Exception {

        Connection conn = getConnection();

        String sql = "UPDATE student SET student_name=?, age=?, course=? WHERE student_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, s.name);
        pst.setInt(2, s.age);
        pst.setString(3, s.course);
        pst.setInt(4, s.student_id);

        int rows = pst.executeUpdate();

        conn.close();

        if(rows > 0)
            return s;

        return null;
    }

    // DELETE
    public static S1 deleteStudent(int id) throws Exception {

        S1 s = fetchStudentById(id);

        if(s == null)
            return null;

        Connection conn = getConnection();

        String sql = "DELETE FROM student WHERE student_id=?";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        pst.executeUpdate();

        conn.close();

        return s;
    }
}