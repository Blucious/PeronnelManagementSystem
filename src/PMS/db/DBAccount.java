package PMS.db;

import PMS.bean.Account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 实现登录账户表操作
public abstract class DBAccount {

    //私有空构造方法,保证本类不能够被实例化。
    private DBAccount() {
    }

    // 查询记录
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

    // 添加记录
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
            JOptionPane.showMessageDialog(null, "账号已存在！");
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return state;
    }
}
