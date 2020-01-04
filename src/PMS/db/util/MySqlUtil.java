package PMS.db.util;

import java.sql.*;

/*
数据库工具类：
    通过调用类的静态方法获得数据库连接。
    该类内置一个Connection对象，外部可以获取该对象，也可以创建新的Connection对象。
    该类提供查询的便利方法。
 */
public abstract class MySqlUtil {
    // 驱动名称
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    // 数据库主机名称
    private static final String SERVER_NAME = "localhost";
    // 数据库名称
    private static final String DATABASE_NAME = "newpms";
    // 数据库连接参数
    private static final String PARAMETERS = "?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
    // 数据库链接地址
    private static final String URL =
            String.format("jdbc:mysql://%s/%s%s",
                    SERVER_NAME, DATABASE_NAME, PARAMETERS);
    // 用户名
    private static final String USER_NAME = "root";
    // 密码
    private static final String USER_PASSWORD = "";

    // 定义连接
    private static volatile Connection conn;


    // 初始化
    static {
        try {
            // 加载MySQL JDBC驱动程序名称
            Class.forName(DRIVER_NAME);
            // 建立连接
            conn = getNewConnection();

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        }
    }

    // 私有空构造方法,保证本类不能够被实例化。
    private MySqlUtil() {
    }

    // 获得新的数据库连接
    public static Connection getNewConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    URL, USER_NAME, USER_PASSWORD); // 建立连接
        } catch (SQLException e) {
            System.out.println("数据库链接异常");
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭指定数据库连接
    public static void closeSpecConnection(Connection conn) {
        if (conn != null) { // 连接是否有效
            try {
                if (!conn.isClosed()) { // 连接是否已关闭
                    conn.close(); // 关闭连接
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取该类内置的数据库连接
    public static Connection getConnection() {
        return conn;
    }

    // 关闭该类内置的数据库连接
    public static void closeConnection() {
        closeSpecConnection(conn);
    }

    public static Statement createStatement() throws SQLException {
        return conn.createStatement();
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    // 测试
    public static void main(String[] args) throws Exception {
        Connection conn = MySqlUtil.getNewConnection();
        String result = "";

        if (conn == null) {
            result = "获得数据库连接错误";
        } else {
            result = "正常获得数据库连接：" + conn;
        }
        closeSpecConnection(conn);

        System.out.println(result);
    }
}
