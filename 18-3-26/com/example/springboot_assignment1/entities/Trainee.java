package com.example.springboot_assignment1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trainee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int traineeId;
	private String traineeName;
	private String traineeDomain;
	private String traineeLocation;
	public int getTraineeId() {
		return traineeId;
	}
	public String getTraineeName() {
		return traineeName;
	}
	public String getTraineeDomain() {
		return traineeDomain;
	}
	public String getTraineeLocation() {
		return traineeLocation;
	}
	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public void setTraineeDomain(String traineeDomain) {
		this.traineeDomain = traineeDomain;
	}
	public void setTraineeLocation(String traineeLocation) {
		this.traineeLocation = traineeLocation;
	}
	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", traineeName=" + traineeName + ", traineeDomain=" + traineeDomain
				+ ", traineeLocation=" + traineeLocation + "]";
	}
	
	

}