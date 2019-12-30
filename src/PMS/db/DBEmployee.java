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

//ʵ��Ա����dep����ɾ���ġ������ݿ������DBEmployee
public class DBEmployee {
	//˽�пչ��췽��,��֤���಻�ܹ���ʵ������
	private DBEmployee(){}
	
	//��ӹ���Ա
	public static void addEmployee(Employee a) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); //�����������
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO emp (empno,empname) VALUES (?,?)");
			ps.setString(1, a.getEmpNo());
			ps.setString(2, a.getEmpName());
			ps.executeUpdate(); // ִ�и��²���
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//ɾ������Ա
	public static void deleteEmployee(String id) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); //�����������
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM emp WHERE empno=?");
			ps.setString(1, id); // ���õ�1��ռλ��������
			ps.executeUpdate();// ִ�и��²���
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//�޸Ĺ���Ա
	public static void updateEmployee(Employee a) {
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection();
			String Sql = "UPDATE emp SET empname=? WHERE empno =?";
			PreparedStatement ps = conn.prepareStatement(Sql);
			ps.setString(1, a.getEmpName());
			ps.setString(2, a.getEmpNo());
		
			ps.executeUpdate(); // ִ�и��²���
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
	}

	//���ݹ���Աid�Ų�ѯ
	public static Employee getEmployee(String id) {
		Employee a = null;
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection();// �����������
			// ����PreparedStatement����ִ��SQL����
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM emp WHERE empno=?");
			ps.setString(1, id); // ���õ�һ��ռλ��������
			ResultSet rs = ps.executeQuery(); // ִ�в�ѯ�����ؽ����
			if (rs.next()) { // ��Ϊ����Աid��Ωһ�ģ�����ֻ����һ������ȿ�
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

	//��ѯAdmin���е����м�¼������Ա�������ص���������
	public static Iterator getAllEmployee() {
		List l = new ArrayList();//�����б�����󣬴�С���Զ�����
		Connection conn = null;
		try {
			conn = MySqlConnnection.getConnection(); // �����������
			Statement stmt = conn.createStatement(); // ����Statement����ִ��SQL����
			ResultSet rs = stmt.executeQuery("SELECT * FROM emp");//ִ�в�ѯ�����ؽ����
			String s = null;//����emp���еĵ�2���ֶΣ�Ա��������
			while (rs.next()) {//ѭ���õ����м�¼������ӵ������б���
				s = rs.getString(2);
				l.add(new Employee(rs.getString(1),rs.getString(2)));
			}
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MySqlConnnection.closeConnection(conn);
		}
		return l.iterator();//���ص���������
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee admin = new Employee("e310", "����");
		DBEmployee.addEmployee(admin);
		for (Iterator it = DBEmployee.getAllEmployee(); it.hasNext();) {
			Employee a1 = (Employee) it.next();
			System.out.println(a1.getEmpNo() + "\t" + a1.getEmpName());
		}
	}

}
