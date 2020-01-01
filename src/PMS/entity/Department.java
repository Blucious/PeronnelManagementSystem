package PMS.entity;

public class Department {
    private String no;
    private String name;

    public Department(String no, String name) {
        setNo(no);
        setName(name);
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
