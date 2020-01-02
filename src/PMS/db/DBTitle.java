package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;
import PMS.entity.Title;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


// 实现账户表的增删改查操作
public final class DBTitle {

    //私有空构造方法,保证本类不能够被实例化。
    private DBTitle() {
    }

    // 添加
    public static boolean add(Title tit) {
        // 查询语句
        String sql = "INSERT INTO title " +
                "(titName) " +
                "VALUES (?)";
        // 执行查询
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        tit.getName());
        // 异常处理
        if (ur.exception != null) {
            // JDBC 异常处理
            // getErrorCode() 获取错误编号。
            // getMessage() 获取错误消息。
            // getSQLState() 获取SQLstate字符串。 对于数据库错误，将返回五位数的XOPEN SQLstate代码。
            // getNextException() 获取异常链中的下一个异常对象。
            // printStackTrace() 将当前异常及其回溯打印到标准错误流。
            // printStackTrace(PrintStream s) 将此可抛弃项及其回溯打印到指定的打印流。
            // printStackTrace(PrintWriter w) 将此可抛弃项及其回溯打印到指定的打印作者。
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 通过账号名删除
     *
     * @param name
     * @return
     */
    public static boolean delete(String name) {
        String sql = "DELETE FROM title WHERE titName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, name);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 通过账户名修改
     */
    public static boolean update(String name, Title tit) {
        String sql = "UPDATE title " +
                "SET titName=? " +
                "WHERE titName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        tit.getName(), name);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 通过账户名字查询
     *
     * @param name
     * @return
     */
    public static Title get(String name) {
        // 查询语句
        String sql = "SELECT * FROM title WHERE titName=?";
        // 创建结果集处理函数
        ResultSetHandler rsh = (ResultSet rs) -> {
            Title t = null;
            if (rs.next()) {
                t = new Title();
                t.setName(rs.getString(1));
            }
            return t;
        };
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rsh, name);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return (Title) qr.result;
    }

    @SuppressWarnings("unchecked cast")
    public static Iterator<Title> getAll() {
        String sql = "SELECT * FROM title";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<Title> l = new LinkedList<>();
            Title t = null;
            while (rs.next()) {
                t = new Title();
                t.setName(rs.getString(1));
                l.add(t);
            }
            return l;
        };

        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rsh);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return ((List<Title>) qr.result).iterator();
    }


    // 测试
    public static void main(String[] args) {
        System.out.println("查询全部：");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Title t = (Title) it.next();
            System.out.println(t);
        }

        Title newTit =
                new Title("测试职位");

        System.out.println("增加+查询：");
        add(newTit);
        System.out.println(get("测试职位"));

        System.out.println("修改+查询：");
        update("测试职位",  new Title("测试职位2"));
        System.out.println(get("测试职位2"));

        System.out.println("删除+查询全部：");
        delete("测试职位2");

        for (Iterator it = getAll(); it.hasNext(); ) {
            Title t = (Title) it.next();
            System.out.println(t);
        }
    }
}
