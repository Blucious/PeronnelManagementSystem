package PMS.db;

import PMS.entity.Department;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//实现Department的添、删、改、查数据库操作类DBDepartment
public abstract class DBDepartment {

    //私有空构造方法,保证本类不能够被实例化。
    private DBDepartment() {
    }

    // 添加
    public static void addDepartment(Department q) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // 获得数据连接
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO department (depNo,depName) VALUES (?,?)");
            ps.setString(1, q.getNo());
            ps.setString(2, q.getName());

            ps.executeUpdate(); // 执行更新操作
            ps.close();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "该编号已存在！不能添加！");
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //删除
    public static void deleteDepartment(String id) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // 获得数据连接
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM department WHERE depNo=?");
            ps.setString(1, id); // 设置第一个占位符的内容
            ps.executeUpdate();  // 执行更新操作
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //修改
    public static void updateDepartment(Department q) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();
            String Sql = "UPDATE department SET depNo=?,depName=? WHERE depNo=?";
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.setString(1, q.getNo());
            ps.setString(2, q.getName());
            ps.setString(3, q.getNo());

            ps.executeUpdate(); // 执行更新操作
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //根据id号查询
    public static Department getDepartment(String id) {
        Department q = null;
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();// 获得数据连接
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM department WHERE depNo=?");
            ps.setString(1, id); // 设置第一个占位符的内容


            ResultSet rs = ps.executeQuery(); //执行查询，返回结果集
            if (rs.next()) { // 因为id是惟一的，所以只返回一个结果既可
                q = new Department(rs.getString(1), rs.getString(2));
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
        return q;
    }

    //查询Department表中的所有记录，返回迭代器对象
    public static Iterator getAllDepartment() {
        List l = new ArrayList(); //数组列表类对象，大小可自动增加
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // 获得数据连接
            Statement stmt = conn.createStatement(); // 建立Statement用于执行SQL操作
            ResultSet rs = stmt.executeQuery("SELECT * FROM department"); // 执行查询
            String s = null;//保存Department表中的第2个字段
            while (rs.next()) {//循环得到所有记录，并添加到数组列表中

                l.add(new Department(rs.getString(1), rs.getString(2)));
            }
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
        return l.iterator();//返回迭代器对象
    }

    //测试DBDepartment类
    public static void main(String[] args) {
        Department newDept = new Department("test", "测试部");
        DBDepartment.addDepartment(newDept);
        DBDepartment.deleteDepartment("test");

        for (Iterator it = DBDepartment.getAllDepartment(); it.hasNext(); ) {
            Department d = (Department) it.next();
            System.out.printf("%s - %s\n", d.getNo(), d.getName());
        }
    }
}
