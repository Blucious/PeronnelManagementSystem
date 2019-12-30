package PMS.db;

import PMS.bean.Account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// ʵ�ֵ�¼�˻������
public abstract class DBAccount {

    //˽�пչ��췽��,��֤���಻�ܹ���ʵ������
    private DBAccount() {
    }

    // ��ѯ��¼
    public static Account getAccount(String userName) {
        Connection conn = null;
        Account account = null;

        try {
            conn = MySqlConnnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM account WHERE accName=?");
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(
                        rs.getString(1),
                        rs.getString(2),
                        true
                );
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return account;
    }

    // ��Ӽ�¼
    public static boolean addAccount(Account account) {
        Connection conn = null;
        boolean state = false;

        try {
            conn = MySqlConnnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO account VALUES (?,?)");
            ps.setString(1, account.getName());
            ps.setString(2, account.getHashedPassword());
            ps.executeUpdate();
            state = true;
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "�˺��Ѵ��ڣ�");
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return state;
    }
}
