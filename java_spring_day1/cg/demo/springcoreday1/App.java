package cg.demo.springcoreday1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cg.demo.springcoreday1.beans.Employee;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("springconfig.xml");

        Employee emp = (Employee) context.getBean("emp");

        emp.display();
    }
}