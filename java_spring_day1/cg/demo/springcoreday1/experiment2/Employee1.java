package cg.demo.springcoreday1.experiment2;

public class Employee1 {

    private int employeeId;
    private String employeeName;
    private double salary;
    private SBU businessUnit; // The SBU object injected via Spring

    // Getters and Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public SBU getBusinessUnit() { return businessUnit; }
    public void setBusinessUnit(SBU businessUnit) { this.businessUnit = businessUnit; }

    // Required by the lab diagram
    public SBU getSbuDetails() {
        return businessUnit;
    }

    // Matches the exact console output in your screenshot
    public void display() {
        System.out.println("Employee details");
        System.out.println("-------------------------------");
        System.out.println("Employee [empId=" + employeeId + ", empName=" + employeeName + ", salary=" + salary + "]");
        System.out.println("sbu details=" + getSbuDetails());
    }
}
