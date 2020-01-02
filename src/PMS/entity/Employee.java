package PMS.entity;

import java.sql.Date;


public class Employee {
    private String no;
    private String name;
    private Date birthday;
    private String depNo;
    private String title;
    private int clockingIn;

    public Employee() {}

    public Employee(String no,
                    String name,
                    Date birthday,
                    String depNo,
                    String title,
                    int clockingIn) {
        setNo(no);
        setName(name);
        setBirthday(birthday);
        setDepNo(depNo);
        setTitle(title);
        setClockingIn(clockingIn);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClockingIn() {
        return clockingIn;
    }

    public void setClockingIn(int clockingIn) {
        this.clockingIn = clockingIn;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", deptNo='" + depNo + '\'' +
                ", title='" + title + '\'' +
                ", clockingIn=" + clockingIn +
                '}';
    }
}
