package PMS.db;

import PMS.entity.Account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 实现账户表的增删改查操作
public abstract class DBAccount {

    //私有空构造方法,保证本类不能够被实例化。
    private DBAccount() {
    }

    // 查询记录
    public static Account getAccount(String userName) {
        Connection conn = null;
        Account acc = null;

        try {
            conn = MySqlConnnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM account WHERE accName=?");
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setName(rs.getString(1));
                acc.setHashedPassword(rs.getString(2), true);
                acc.setEmpNo(rs.getString(3));
                acc.setPrivilege(rs.getString(4));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return acc;
    }

    // 添加记录
    public static boolean addAccount(Account acc) {
        Connection conn = null;
        boolean state = false;

        try {
            conn = MySqlConnnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO account VALUES (?,?,?,?)");
            ps.setString(1, acc.getName());
            ps.setString(2, acc.getHashedPassword());
            ps.setString(3, acc.getEmpNo());
            ps.setString(4, acc.getPrivilege());

            ps.executeUpdate();
            ps.close();
            state = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "账号已存在！");
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return state;
    }

    // 测试
    public static void main(String[] args) {
        Account acc = getAccount("admin");
        System.out.println(acc);
    }
}
