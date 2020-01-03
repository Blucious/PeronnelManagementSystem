package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;
import PMS.entity.Employee;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//实现员工表dep的添、删、改、查数据库操作类DBEmployee

public class DBEmployee {

    //私有空构造方法,保证本类不能够被实例化。
    private DBEmployee() {
    }

    //添加员工
    public static boolean add(Employee e) {
        String sql = "INSERT INTO employee "
                + "(empNo,empName,empBirthday,empDepNo,empTitle,empClockingIn) "
                + "VALUES (?,?,?,?,?,?)";

        DBAccessUtil.UpdateResult ur = DBAccessUtil.updateWrapped(sql,
                e.getNo(), e.getName(), e.getBirthday(),
                e.getDepNo(), e.getTitle(), e.getClockingIn());
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    //删除员工
    public static boolean delete(String no) {
        DBAccessUtil.UpdateResult ur = DBAccessUtil.updateWrapped(
                "DELETE FROM employee WHERE empNo=?", no);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }
        return ur.state;
    }

    /**
     * 修改员工
     */
    public static boolean update(String no, Employee e) {
        String sql = "UPDATE employee " +
                "SET empNo=?, empName=?, empBirthday=?, empDepNo=?, " +
                "empTitle=?, empClockingIn=? " +
                "WHERE empNo=?";
        DBAccessUtil.UpdateResult ur = DBAccessUtil.updateWrapped(sql,
                e.getNo(), e.getName(), e.getBirthday(),
                e.getDepNo(), e.getTitle(), e.getClockingIn(), no);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }
        return ur.state;
    }

    //根据员工id号查询
    public static Employee get(String no) {
        String sql = "SELECT * FROM employee WHERE empNo=?";
        ResultSetHandler rsh = (ResultSet rs) -> {
            Employee emp = null;
            if (rs.next()) { // 因为员工no是惟一的，所以只返回一个结果既可
                emp = new Employee();
                emp.setNo(rs.getString(1));
                emp.setName(rs.getString(2));
                emp.setBirthday(rs.getDate(3));
                emp.setDepNo(rs.getString(4));
                emp.setTitle(rs.getString(5));
                emp.setClockingIn(rs.getInt(6));
            }
            return emp;
        };

        DBAccessUtil.QueryResult qr = DBAccessUtil.queryWrapped(sql, rsh, no);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return (Employee) qr.result;
    }

    //查询表中的所有记录（管理员），返回迭代器对象
    @SuppressWarnings("unchecked cast")
    public static Iterator<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<Employee> l = new LinkedList<>();
            Employee e = null;
            while (rs.next()) { // 因为员工no是惟一的，所以只返回一个结果既可
                e = new Employee();
                e.setNo(rs.getString(1));
                e.setName(rs.getString(2));
                e.setBirthday(rs.getDate(3));
                e.setDepNo(rs.getString(4));
                e.setTitle(rs.getString(5));
                e.setClockingIn(rs.getInt(6));
                l.add(e);
            }
            return l;
        };

        DBAccessUtil.QueryResult qr = DBAccessUtil.queryWrapped(sql, rsh);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return ((List<Employee>) qr.result).iterator();
    }

    // 测试
    public static void main(String[] args) {
        System.out.println("查询全部：");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Employee e = (Employee) it.next();
            System.out.println(e);
        }

        Employee newEmp = new Employee(
                "e310", "张三", Date.valueOf("1998-5-4"),
                "1001", "员工", 2);

        System.out.println("增加+查询：");
        add(newEmp);
        System.out.println(get("e310"));

        System.out.println("修改+查询：");
        update("e310", new Employee(
                        "e310", "李四", Date.valueOf("1998-5-4"),
                        "1001", "员工", 2));
        System.out.println(get("e310"));

        System.out.println("删除+查询全部：");
        delete("e310");

        for (Iterator it = getAll(); it.hasNext(); ) {
            Employee e = (Employee) it.next();
            System.out.println(e);
        }
    }

}
