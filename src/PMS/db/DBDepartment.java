package PMS.db;

import examch1.db.MySqlConnnection;
import examch1.mybean.Department;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//ʵ��Department����ɾ���ġ������ݿ������DBDepartment
public abstract class DBDepartment {

    //˽�пչ��췽��,��֤���಻�ܹ���ʵ������
    private DBDepartment() {
    }

    // ���
    public static void addDepartment(Department q) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // �����������
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Dep (depNo,depName)VALUES (?,?)");
            ps.setString(1, q.getDepNo());
            ps.setString(2, q.getDepName());

            ps.executeUpdate(); // ִ�и��²���
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "�ñ���Ѵ��ڣ�������ӣ�");
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //ɾ��
    public static void deleteDepartment(String id) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // �����������
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM dep WHERE depNo=?");
            ps.setString(1, id); // ���õ�һ��ռλ��������
            ps.executeUpdate();  // ִ�и��²���
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //�޸�
    public static void updateDepartment(Department q) {
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();
            String Sql = "UPDATE dep SET depNo =?,depName =? WHERE depNo =?";
            PreparedStatement ps = conn.prepareStatement(Sql);
            ps.setString(1, q.getDepNo());
            ps.setString(2, q.getDepName());
            ps.setString(3, q.getDepNo());

            ps.executeUpdate(); // ִ�и��²���
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
    }

    //����id�Ų�ѯ
    public static Department getDepartment(String id) {
        Department q = null;
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection();// �����������
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM dep WHERE depNo=?");
            ps.setString(1, id); // ���õ�һ��ռλ��������


            ResultSet rs = ps.executeQuery(); //ִ�в�ѯ�����ؽ����
            if (rs.next()) { // ��Ϊid��Ωһ�ģ�����ֻ����һ������ȿ�
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

    //��ѯDepartment���е����м�¼�����ص���������
    public static Iterator getAllDepartment() {
        List l = new ArrayList(); //�����б�����󣬴�С���Զ�����
        Connection conn = null;
        try {
            conn = MySqlConnnection.getConnection(); // �����������
            Statement stmt = conn.createStatement(); // ����Statement����ִ��SQL����
            ResultSet rs = stmt.executeQuery("SELECT * FROM dep"); // ִ�в�ѯ
            String s = null;//����Department���еĵ�2���ֶ�
            while (rs.next()) {//ѭ���õ����м�¼������ӵ������б���

                l.add(new Department(rs.getString(1), rs.getString(2)));
            }
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }
        return l.iterator();//���ص���������
    }

    //����DBDepartment��
    public static void main(String[] args) {
        Department q1 = new Department("400s", "���Բ�");
        //	DBDepartment.addDepartment(q1);
        DBDepartment.deleteDepartment("ch1_2");


        for (Iterator it = DBDepartment.getAllDepartment(); it.hasNext(); ) {
            Department c = (Department) it.next();
            System.out.println("@" + c.getDepNo() + "\n@" + c.getDepName()
                    + "\n");
        }
    }
}
