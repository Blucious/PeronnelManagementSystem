package PMS.db;

import PMS.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//实现员工表dep的添、删、改、查数据库操作类DBEmployee

public class DBEmployee {

    //私有空构造方法,保证本类不能够被实例化。
    private DBEmployee() {
    }

    //添加员工
    public static void addEmployee(Employee a) {
        Connection conn = null;
        try {
            //获得数据连接
            conn = MySqlConnnection.getConnection();

            // 建立PreparedStatement用于执行SQL操作
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO employee VALUES (?,?,?,?,?,?)");

            // 设置占位符的内容
            ps.setString(1, a.getNo());
            ps.setString(2, a.getName());
            ps.setDate(3, a.getBirthday());
            ps.setString(4, a.getDeptNo());
            ps.setString(5, a.getTitle());
            ps.setInt(6, a.getClockingIn());

            // 执行更新操作
            ps.executeUpdate();

            // 关闭
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //删除员工
    public static void deleteEmployee(String id) {
        Connection conn = null;
        try {
            //获得数据连接
            conn = MySqlConnnection.getConnection();

            // 建立PreparedStatement用于执行SQL操作
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM employee WHERE empNo=?");
            // 设置占位符的内容
            ps.setString(1, id); // 设置第1个占位符的内容

            // 执行更新操作
            ps.executeUpdate();
            ps.close(); // 关闭
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //修改员工
    public static void updateEmployee(Employee a) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    String.join("",
                            "UPDATE employee ",
                            "SET empName=?, empBirthday=?, empDeptNo=?, " +
                                    "empTitle=?， empClockingIn=?",
                            "WHERE empNo=?")
            );
            // SET子句
            ps.setString(1, a.getName());
            ps.setDate(2, a.getBirthday());
            ps.setString(3, a.getDeptNo());
            ps.setString(4, a.getTitle());
            ps.setInt(5, a.getClockingIn());
            // WHERE子句
            ps.setString(6, a.getNo());

            // 执行更新操作
            ps.executeUpdate();
            // 关闭
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //根据员工id号查询
    public static Employee getEmployee(String id) {
        Employee emp = null;
        Connection conn = null;
        try {
            // 获得数据连接
            conn = MySqlConnnection.getConnection();

            // 建立PreparedStatement用于执行SQL操作
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM employee WHERE empNo=?");
            ps.setString(1, id); // 设置第一个占位符的内容

            // 执行查询，返回结果集
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // 因为员工id是惟一的，所以只返回一个结果既可
                emp = new Employee();
                emp.setNo(rs.getString(1));
                emp.setName(rs.getString(2));
                emp.setBirthday(rs.getDate(3));
                emp.setDeptNo(rs.getString(4));
                emp.setTitle(rs.getString(5));
                emp.setClockingIn(rs.getInt(6));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
        return emp;
    }

    //查询表中的所有记录（管理员），返回迭代器对象
    public static Iterator getAllEmployee() {
        List<Employee> list = new ArrayList<>();//数组列表类对象，大小可自动增加
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();

            Statement stmt = conn.createStatement(); // 建立Statement用于执行SQL操作
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee"); //执行查询，返回结果集
            while (rs.next()) { //循环得到所有记录，并添加到数组列表中
                Employee emp = new Employee();
                emp.setNo(rs.getString(1));
                emp.setName(rs.getString(2));
                emp.setBirthday(rs.getDate(3));
                emp.setDeptNo(rs.getString(4));
                emp.setTitle(rs.getString(5));
                emp.setClockingIn(rs.getInt(6));
                list.add(emp);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
        return list.iterator();//返回迭代器对象
    }

    public static void main(String[] args) {
        Employee admin = new Employee(
        		"e310", "张三", Date.valueOf("1998-5-4"),
				"1001", "员工", 2);
        DBEmployee.addEmployee(admin);

        for (Iterator it = DBEmployee.getAllEmployee(); it.hasNext(); ) {
            Employee a1 = (Employee) it.next();
            System.out.println(a1.getNo() + "\t" + a1.getName());
        }
    }

}
