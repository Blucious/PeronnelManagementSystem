package PMS.db;

import PMS.bean.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccount {

    //私有空构造方法,保证本类不能够被实例化。
    private DBAccount() {
    }

    // 密码查询
    public static String getHashedPassword(String userName) {
        Connection conn = null;
        String hashedPassword = null;

        try {
            conn = MySqlConnnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT accHashedPassword FROM account WHERE accName=?");
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hashedPassword = rs.getString(1);
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnnection.closeConnection(conn);
        }

        return hashedPassword;
    }
}
