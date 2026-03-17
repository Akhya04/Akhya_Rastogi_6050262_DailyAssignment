package cg.demo.springcoreday1.experiment3;

public class Employee2 {

    private int employeeId;
    private String employeeName;
    private double salary;
    private int age; 

    // Getters and Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // Formats perfectly to match the array output in the lab manual
    @Override
    public String toString() {
        return "Employee [empAge=" + age + ", empId=" + employeeId + ", empName=" + employeeName + ", empSalary=" + salary + "]";
    }
}