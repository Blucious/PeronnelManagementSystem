package PMS.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//���ݿ�������MySqlConnnection��ͨ��������ľ�̬����������ݿ����ӡ�
public abstract class MySqlConnnection {

    //˽�пչ��췽��,��֤���಻�ܹ���ʵ������
    private MySqlConnnection() {
    }

    //������ݿ�����
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //����MySQL JDBC ������������
//			Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            //���ݿ����Ӳ�����
            String serverName = "localhost"; 	// ���ݿ���������
            String dbName = "pms";   		// ���ݿ�����
            String url = "jdbc:mysql://" + serverName + "/" + dbName;
            String username = "root";    		//MySql�û���
            String password = "";  				//MySql����
            conn = DriverManager.getConnection(url, username, password); //��������
        } catch (ClassNotFoundException | SQLException e) {
			// ��ӡ�쳣
			// �Ҳ���MySql���������� ClassNotFoundException
			// ������ݿ����ӷ����쳣 SQLException
			e.printStackTrace();
		}
		return conn;
    }

    //�ر����ݿ�����
    public static void closeConnection(Connection conn) {
        if (conn != null) { //�����Ƿ���Ч
            try {
                if (!conn.isClosed()) { //�����Ƿ��ѹر�
                    conn.close(); //�ر�����
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //����������
    public static void main(String[] args) throws Exception {
        Connection conn = MySqlConnnection.getConnection();
        String result = "";
        if (conn == null) {
            result = "������ݿ����Ӵ���.";
        } else {
            result = "����������ݿ�����." + conn;
        }
        JOptionPane.showMessageDialog(null, result);
    }
}
