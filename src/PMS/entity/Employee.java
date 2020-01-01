package PMS.entity;

import java.sql.Date;


public class Employee {
    private String no;
    private String name;
    private Date birthday;
    private String deptNo;
    private String title;
    private int clockingIn;

    public Employee() {}

    public Employee(String no,
                    String name,
                    Date birthday,
                    String deptNo,
                    String title,
                    int clockingIn) {
        setNo(no);
        setName(name);
        setBirthday(birthday);
        setDeptNo(deptNo);
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

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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
}
