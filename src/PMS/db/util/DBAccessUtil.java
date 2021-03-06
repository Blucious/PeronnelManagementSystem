package PMS.db.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体增删改查模板类
 * 参考：【JDBC工具类】——https://www.jianshu.com/p/82a9e124be60
 * <p>
 * 所有异常均交由外部处理
 */
public final class DBAccessUtil {

    //私有空构造方法,保证本类不能够被实例化。
    private DBAccessUtil() {
    }

    /**
     * 实现增删改的抽象
     *
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     */
    public static int update(String sql, Object... args) throws SQLException {
        // 准备查询语句
        PreparedStatement ps = MySqlUtil.prepareStatement(sql);
        // 设置占位符
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        }

        // 执行更新
        int result = ps.executeUpdate();

        // 必须关闭PreparedStatement和ResultSet，否则有可能会照成内存泄漏
        // https://stackoverflow.com/questions/14546592/do-i-need-to-close-preparedstatement
        ps.close();

        return result; // 返回值：有多少行被修改
    }

    public static class UpdateResult {
        public boolean state = false; // 查询是否成功
        public SQLException exception = null; // 存放异常内容，如果没有发生异常则为null

        public UpdateResult() {
        }

        public UpdateResult(boolean state, SQLException exception) {
            this.state = state;
            this.exception = exception;
        }
    }

    /**
     * 对update进一步封装，简化外部调用语句
     */
    public static UpdateResult updateWrapped(String sql, Object... args) {
        UpdateResult ur = new UpdateResult();

        try {
            update(sql, args);
            ur.state = true;
        } catch (SQLException e) {
            ur.exception = e;
        }

        return ur;
    }

    /**
     * 实现查询操作
     *
     * @param sql
     * @param handler
     * @param args
     * @return
     */
    public static Object query(
            String sql,
            ResultSetHandler handler,
            Object... args) throws SQLException {
        // 准备查询语句
        PreparedStatement ps = MySqlUtil.prepareStatement(sql);
        // 设置占位符
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        }
        // 执行查询
        ResultSet rs = ps.executeQuery();

        // 调用结果集处理方法处理结果集，并返回处理的结果
        Object result = handler.doHandler(rs);

        rs.close();
        ps.close(); // 关闭PreparedStatement，否则有内存泄漏的可能

        return result;
    }

    public static class QueryResult {
        public Object result = null; // 查询结果
        public SQLException exception = null; // 存放异常内容，如果没有发生异常则为null

        public QueryResult() {
        }

        public QueryResult(Object result, SQLException exception) {
            this.result = result;
            this.exception = exception;
        }
    }

    /**
     * 对query进一步封装，简化外部调用语句
     */
    public static QueryResult queryWrapped(
            String sql,
            ResultSetHandler handler,
            Object... args) {
        QueryResult qr = new QueryResult();

        try {
            qr.result = query(sql, handler, args);
        } catch (SQLException e) {
            qr.exception = e;
        }

        return qr;
    }

    public static void printAllExceptionInformation(SQLException e) {
        // JDBC 异常处理
        // getErrorCode() 获取错误编号。
        // getMessage() 获取错误消息。
        // getSQLState() 获取SQLstate字符串。 对于数据库错误，将返回五位数的XOPEN SQLstate代码。
        // getNextException() 获取异常链中的下一个异常对象。
        // printStackTrace() 将当前异常及其回溯打印到标准错误流。
        // printStackTrace(PrintStream s) 将此可抛弃项及其回溯打印到指定的打印流。
        // printStackTrace(PrintWriter w) 将此可抛弃项及其回溯打印到指定的打印作者。

        System.out.println("Message:");
        System.out.println(e.getMessage());
        System.out.println("ErrorCode:");
        System.out.println(e.getErrorCode());
        System.out.println("SQLState:");
        System.out.println(e.getSQLState());
        System.out.println("StackTrace：");
        e.printStackTrace();
    }

    public static class TableModelData {
        public List<String> columnNames;
        public List<List<Object>> valuesList;
    }

    public static TableModelData queryTableModelData(String sql, Object... args) {
        TableModelData tmd = null;

        try {
            // 查询
            PreparedStatement ps = MySqlUtil.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            // 获取结果集
            ResultSet rs = ps.executeQuery();

            // 将指针移动到此 ResultSet 对象的最后一行。以获取当前行的编号，也就是记录数量（没有记录则为0）
            rs.last();
            int numberOfRows = rs.getRow();
            // 获取结果集的元数据，也就是列的编号、类型和属性
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount(); // 得到ResultSet对象中的列数


            // 开始设置数据
            tmd = new TableModelData();

            // 读取列名
            tmd.columnNames = new ArrayList<>(numberOfColumns);
            for (int i = 0; i < numberOfColumns; i++) {
                // 读取列的别名（没有就原名）
                tmd.columnNames.add(rsmd.getColumnLabel(i + 1));
            }

            // 读取记录
            tmd.valuesList = new ArrayList<>(numberOfRows);
            for (int i = 0; i < numberOfRows; i++) {
                List<Object> values = new ArrayList<>(numberOfColumns);
                rs.absolute(i + 1); // 游标定位到指定的行
                for (int j = 0; j < numberOfColumns; j++) {
                    values.add(rs.getObject(j + 1));
                }
                tmd.valuesList.add(values);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmd;
    }
}

