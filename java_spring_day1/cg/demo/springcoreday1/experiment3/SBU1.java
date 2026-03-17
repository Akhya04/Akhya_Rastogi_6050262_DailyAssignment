package cg.demo.springcoreday1.experiment3;

import java.util.List;

public class SBU1 {

    private String sbuCode; 
    private String sbuName;
    private String sbuHead;
    private List<Employee2> empList; // Injects a List of Employee2 objects

    // Getters and Setters
    public String getSbuCode() { return sbuCode; }
    public void setSbuCode(String sbuCode) { this.sbuCode = sbuCode; }

    public String getSbuName() { return sbuName; }
    public void setSbuName(String sbuName) { this.sbuName = sbuName; }

    public String getSbuHead() { return sbuHead; }
    public void setSbuHead(String sbuHead) { this.sbuHead = sbuHead; }

    public List<Employee2> getEmpList() { return empList; }
    public void setEmpList(List<Employee2> empList) { this.empList = empList; }

    // Matches the exact console output required
    public void display() {
        System.out.println("SBU details");
        System.out.println("----------------------------");
        System.out.println("sbuCode=" + sbuCode + ", sbuHead=" + sbuHead + ", sbuName=" + sbuName);
        System.out.println("Employee Details:----------------");
        System.out.println(empList); 
    }
}