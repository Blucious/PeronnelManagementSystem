package PMS.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Employee {
    private String no;
    private String name;
    private Date birthday;
    private String depNo;
    private String title;

    private final static SimpleDateFormat simpleDateFormat;
    static {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Employee() {}

    public Employee(String no,
                    String name,
                    Date birthday,
                    String depNo,
                    String title) {
        setNo(no);
        setName(name);
        setBirthday(birthday);
        setDepNo(depNo);
        setTitle(title);
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

    public String getBirthdayString() {
        return simpleDateFormat.format(birthday);
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

    @Override
    public String toString() {
        return "Employee{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", deptNo='" + depNo + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (no != null ? !no.equals(employee.no) : employee.no != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        if (depNo != null ? !depNo.equals(employee.depNo) : employee.depNo != null) return false;
        return title != null ? title.equals(employee.title) : employee.title == null;
    }

    @Override
    public int hashCode() {
        int result = no != null ? no.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (depNo != null ? depNo.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
