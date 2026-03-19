package com.example.springboot_assignment1;
import java.util.*;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.springboot_assignment1.entities.Trainee;
import com.example.springboot_assignment1.services.TraineeService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	TraineeService ts;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		int choice;
		List<Trainee> t=new ArrayList<>();
		do {
			System.out.println("1:print all");
			System.out.println("2:print by id");
			System.out.println("3:delete by id");
			System.out.println("4:insert");
			System.out.println("5:update");
			System.out.println("6:terminate");
			System.out.println("Enter choice?");
			Scanner sc=new Scanner(System.in);
			choice =sc.nextInt();
			switch(choice)
			{
			case 1:
				t=ts.fetchAll();
				for(Trainee tt:t)
				{
					System.out.println(tt);
				}
				break;
			case 2:
				System.out.println("Enter id");
				int i=sc.nextInt();
				Optional<Trainee> tt=ts.findById(i);
				System.out.println(tt);
				break;
			case 3:
				System.out.println("Enter id");
				int i1=sc.nextInt();
				ts.deleteById(i1);
				break;
			case 4:
				ts.insert();
				break;
			case 5:
				ts.update();
				break;
			case 6:
				System.out.println("Terminate");
				break;
			default:
				System.out.println("Wrong choice");
				
			}
			
		}
		while(choice !=6);
		
	}

}