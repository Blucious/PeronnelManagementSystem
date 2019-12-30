package PMS.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//数据库连接类MySqlConnnection。通过调用类的静态方法获得数据库连接。
public abstract class MySqlConnnection {

    //私有空构造方法,保证本类不能够被实例化。
    private MySqlConnnection() {
    }

    //获得数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //加载MySQL JDBC 驱动程序名称
//			Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            //数据库连接参数。
            String serverName = "localhost"; 	// 数据库主机名称
            String dbName = "pms";   		// 数据库名称
            String url = "jdbc:mysql://" + serverName + "/" + dbName;
            String username = "root";    		//MySql用户名
            String password = "";  				//MySql密码
            conn = DriverManager.getConnection(url, username, password); //建立连接
        } catch (ClassNotFoundException | SQLException e) {
			// 打印异常
			// 找不到MySql驱动程序类 ClassNotFoundException
			// 获得数据库连接发生异常 SQLException
			e.printStackTrace();
		}
		return conn;
    }

    //关闭数据库连接
    public static void closeConnection(Connection conn) {
        if (conn != null) { //连接是否有效
            try {
                if (!conn.isClosed()) { //连接是否已关闭
                    conn.close(); //关闭连接
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //测试连接类
    public static void main(String[] args) throws Exception {
        Connection conn = MySqlConnnection.getConnection();
        String result = "";
        if (conn == null) {
            result = "获得数据库连接错误.";
        } else {
            result = "正常获得数据库连接." + conn;
        }
        JOptionPane.showMessageDialog(null, result);
    }
}
