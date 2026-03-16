package cg.demo.springcoreday1.experiment2;

public class SBU {

    private String sbuId; 
    private String sbuName;
    private String sbuHead;

    // Getters and Setters
    public String getSbuId() { return sbuId; }
    public void setSbuId(String sbuId) { this.sbuId = sbuId; }

    public String getSbuName() { return sbuName; }
    public void setSbuName(String sbuName) { this.sbuName = sbuName; }

    public String getSbuHead() { return sbuHead; }
    public void setSbuHead(String sbuHead) { this.sbuHead = sbuHead; }

    // Formats the output exactly as required by the lab
    @Override
    public String toString() {
        return "SBU [sbuCode=" + sbuId + ", sbuHead=" + sbuHead + ", sbuName=" + sbuName + "]";
    }
}