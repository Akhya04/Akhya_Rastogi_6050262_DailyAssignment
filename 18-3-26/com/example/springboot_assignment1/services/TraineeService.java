package com.example.springboot_assignment1.services;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_assignment1.entities.Trainee;
import com.example.springboot_assignment1.repo.TraineeJpaRepository;


@Service
public class TraineeService {
	Scanner sc=new Scanner(System.in);
	@Autowired
	private TraineeJpaRepository traineeService;
	public  List<Trainee> fetchAll(){
		return traineeService.findAll();
	}
	public Optional<Trainee> findById(int id) {
		 return traineeService.findById(id);
		
	}
	public void deleteById(int id) {
		if(traineeService.existsById(id))
		{
			traineeService.deleteById(id);
		}
		else
		{
			System.out.println("Not available id");
		}
	}
	public void insert()
	{
		Trainee t=new Trainee();
		System.out.println("Enter trainee name");
		String name=sc.next();
		t.setTraineeName(name);
		System.out.println("Enter trainee domain");
		String d=sc.next();
		t.setTraineeDomain(d);
		System.out.println("Enter trainee location");
		String l=sc.next();
		t.setTraineeLocation(l);
		traineeService.save(t);
	}
	public void update() {
		Trainee t=new Trainee();
		System.out.println("Enter trainee id you want to update");
		int i=sc.nextInt();
		if(traineeService.existsById(i))
		{
			t.setTraineeId(i);
			System.out.println("Enter trainee name");
			String name=sc.next();
			t.setTraineeName(name);
			System.out.println("Enter trainee domain");
			String d=sc.next();
			t.setTraineeDomain(d);
			System.out.println("Enter trainee location");
			String l=sc.next();
			t.setTraineeLocation(l);
			traineeService.save(t);
			
		}
		else
		{
			System.out.println("Id is wrong");
		}
		
	}
	

}