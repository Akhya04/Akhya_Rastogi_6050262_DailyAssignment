package cg.demo.springcoreday1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cg.demo.springcoreday1.experiment2.Employee1;

public class App1{

    public static void main(String[] args) {
        
        // 1. Load the Spring Application Context
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig1.xml");
        
        // 2. Retrieve the fully injected bean (Notice it uses Employee1 now)
        Employee1 employee = (Employee1) context.getBean("emp");
        
        // 3. Display the values
        employee.display();
    }
}
