package PMS.bean;

public class Department {
    private String depNo;
    private String depName;

    public Department() {
    }

    public Department(String depNo, String depName) {
        this.depNo = depNo;
        this.depName = depName;

    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
