package PMS.bean;

import java.sql.Date;

public class Employee extends Account {
    private String no;
    private Date birthday;
    private int clockingIn;
    private String title;
    private String dept;

    public Employee(String name, String password, boolean passwordIsHashed) {
        super(name, password, passwordIsHashed);
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getClockingIn() {
        return clockingIn;
    }

    public void setClockingIn(int clockingIn) {
        this.clockingIn = clockingIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
