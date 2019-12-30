package PMS.db;

import examch1.db.MySqlConnnection;
import examch1.mybean.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//实现员工表dep的添、删、改、查数据库操作类DBEmployee
public class DBEmployee {
	//私有空构造方法,保证本类不能够被实例化。
	private DBEmployee(){}
	
	//添加管理员
	public static void addEmployee(Employee a) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); //获得数据连接
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO emp (empno,empname) VALUES (?,?)");
			ps.setString(1, a.getEmpNo());
			ps.setString(2, a.getEmpName());
			ps.executeUpdate(); // 执行更新操作
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//删除管理员
	public static void deleteEmployee(String id) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); //获得数据连接
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM emp WHERE empno=?");
			ps.setString(1, id); // 设置第1个占位符的内容
			ps.executeUpdate();// 执行更新操作
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//修改管理员
	public static void updateEmployee(Employee a) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection();
			String Sql = "UPDATE emp SET empname=? WHERE empno =?";
			PreparedStatement ps = conn.prepareStatement(Sql);
			ps.setString(1, a.getEmpName());
			ps.setString(2, a.getEmpNo());
		
			ps.executeUpdate(); // 执行更新操作
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//根据管理员id号查询
	public static Employee getEmployee(String id) {
		Employee a = null;
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection();// 获得数据连接
			// 建立PreparedStatement用于执行SQL操作
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM emp WHERE empno=?");
			ps.setString(1, id); // 设置第一个占位符的内容
			ResultSet rs = ps.executeQuery(); // 执行查询，返回结果集
			if (rs.next()) { // 因为管理员id是惟一的，所以只返回一个结果既可
				a = new Employee(rs.getString(1),rs.getString(2));
			}
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
		return a;
	}

	//查询Admin表中的所有记录（管理员），返回迭代器对象
	public static Iterator getAllEmployee() {
		List l = new ArrayList();//数组列表类对象，大小可自动增加
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); // 获得数据连接
			Statement stmt = conn.createStatement(); // 建立Statement用于执行SQL操作
			ResultSet rs = stmt.executeQuery("SELECT * FROM emp");//执行查询，返回结果集
			String s = null;//保存emp表中的第2个字段（员工姓名）
			while (rs.next()) {//循环得到所有记录，并添加到数组列表中
				s = rs.getString(2);
				l.add(new Employee(rs.getString(1),rs.getString(2)));
			}
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
		return l.iterator();//返回迭代器对象
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee admin = new Employee("e310", "张三");
		DBEmployee.addEmployee(admin);
		for (Iterator it = DBEmployee.getAllEmployee(); it.hasNext();) {
			Employee a1 = (Employee) it.next();
			System.out.println(a1.getEmpNo() + "\t" + a1.getEmpName());
		}
	}

}
