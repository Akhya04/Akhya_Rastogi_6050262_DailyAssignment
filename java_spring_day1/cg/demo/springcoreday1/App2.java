package cg.demo.springcoreday1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cg.demo.springcoreday1.experiment3.SBU1; // <-- This fixes your red X error!

public class App2 {

    public static void main(String[] args) {
        
        // 1. Load the Spring Application Context using springconfig2.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig2.xml");
        
        // 2. Retrieve the SBU bean
        SBU1 sbu = (SBU1) context.getBean("sbuBean");
        
        // 3. Display the values
        sbu.display();
    }
}