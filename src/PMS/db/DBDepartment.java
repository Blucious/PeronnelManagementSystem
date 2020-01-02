package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;

import PMS.entity.Department;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


//实现Department的添、删、改、查数据库操作类DBDepartment
public abstract class DBDepartment {

    //私有空构造方法,保证本类不能够被实例化。
    private DBDepartment() {
    }

    /**
     * 添加
     * @param q
     * @return
     */
    public static boolean add(Department q) {
        boolean state = false;

        String sql = "INSERT INTO department (depNo,depName) VALUES (?,?)";

        try {
            DBAccessUtil.update(sql, q.getNo(), q.getName());
            state = true;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "该编号已存在！不能添加！");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return state;
    }

    //删除
    public static boolean delete(String no) {
        boolean state = false;

        String sql = "DELETE FROM department WHERE depNo=?";
        try {
            DBAccessUtil.update(sql, no);
            state = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return state;
    }

    /**
     * 修改
     * @param no 要修改的部门号
     * @param q 部门实体
     */
    public static boolean update(String no, Department q) {
        boolean state = false;

        String sql = "UPDATE department SET depNo=?,depName=? WHERE depNo=?";
        try {
            DBAccessUtil.update(sql, q.getNo(), q.getName(), no);
            state = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    //根据no号查询
    public static Department get(String no) {
        Department dep = null;

        String sql = "SELECT * FROM department WHERE depNo=?";
        ResultSetHandler rsh = (ResultSet rs) -> {
            Department q = null;
            if (rs.next()) {
                q = new Department();
                q.setNo(rs.getString(1));
                q.setName(rs.getString(2));
            }
            return q;
        };

        try {
            dep = (Department)DBAccessUtil.query(sql, rsh, no);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dep;
    }

    //查询Department表中的所有记录，返回迭代器对象
    public static Iterator<Department> getAll() {
        List<Department> list = new ArrayList<>(); //数组列表类对象，大小可自动增加

        String sql = "SELECT * FROM department";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<Department> l = new LinkedList<>();
            while (rs.next()) {//循环得到所有记录，并添加到数组列表中
                Department d = new Department();
                d.setNo(rs.getString(1));
                d.setName(rs.getString(2));
                l.add(d);
            }
            return l;
        };

        try {
            list = (List<Department>)DBAccessUtil.query(sql, rsh, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.iterator();//返回迭代器对象
    }

    //测试
    public static void main(String[] args) {
        System.out.println("查询全部：");
        for (Iterator it = DBDepartment.getAll(); it.hasNext(); ) {
            Department d = (Department) it.next();
            System.out.println(d);
        }

        Department newDept = new Department("test", "测试部");

        System.out.println("增加+查询：");
        DBDepartment.add(newDept);
        System.out.println(DBDepartment.get("test"));

        System.out.println("修改+查询：");
        DBDepartment.update("test", new Department("test", "测试2部"));
        System.out.println(DBDepartment.get("test"));

        System.out.println("删除+查询全部：");
        DBDepartment.delete("test");

        for (Iterator it = DBDepartment.getAll(); it.hasNext(); ) {
            Department d = (Department) it.next();
            System.out.println(d);
//            System.out.printf("%s - %s\n", d.getNo(), d.getName());
        }
    }
}
