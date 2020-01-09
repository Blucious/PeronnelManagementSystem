package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.MySqlUtil;
import PMS.db.util.ResultSetHandler;
import PMS.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


// 实现账户表的增删改查操作
public final class DBAccount {

    //私有空构造方法,保证本类不能够被实例化。
    private DBAccount() {
    }

    // 添加
    public static boolean add(Account acc) {
        // 查询语句
        String sql = "INSERT INTO account " +
                "(accName,accHashedPassword,accEmpNo,accPrivilege) " +
                "VALUES (?,?,?,?)";
        // 执行查询
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        acc.getName(),
                        acc.getHashedPassword(),
                        acc.getEmpNo(),
                        acc.getPrivilege());
        // 异常处理
        if (ur.exception != null) {
            // 1062 Duplicate entry 'stu1' for key 'PRIMARY'
            if (ur.exception.getErrorCode() != 1062) {
                ur.exception.printStackTrace();
            }
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
        String sql = "DELETE FROM account WHERE accName=?";
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
    public static boolean update(String name, Account acc) {
        String sql = "UPDATE account " +
                "SET accName=?, accHashedPassword=?, " +
                "accEmpNo=?, accPrivilege=? " +
                "WHERE accName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        acc.getName(), acc.getHashedPassword(),
                        acc.getEmpNo(), acc.getPrivilege(), name);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }


    // 创建结果集处理函数，单结果通用
    private static ResultSetHandler rshSingle = (ResultSet rs) -> {
        Account a = null;
        if (rs.next()) {
            a = new Account();
            a.setName(rs.getString(1));
            a.setHashedPassword(rs.getString(2), true);
            a.setEmpNo(rs.getString(3));
            a.setPrivilege(rs.getString(4));
        }
        return a;
    };

    /**
     * 通过账户名字查询
     *
     * @param name
     * @return
     */
    public static Account get(String name) {
        // 查询语句
        String sql = "SELECT * FROM account WHERE accName=?";
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rshSingle, name);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return (Account) qr.result;
    }

    public static Account getByEmpNo(String no) {
        // 查询语句
        String sql = "SELECT * FROM account WHERE accEmpNo=?";
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rshSingle, no);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return (Account) qr.result;
    }

    @SuppressWarnings("unchecked cast")
    public static Iterator<Account> getAll() {
        String sql = "SELECT * FROM account";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<Account> l = new LinkedList<>();
            Account a = null;
            while (rs.next()) { // 因为账户名是唯一的，所以只返回一个结果既可
                a = new Account();
                a.setName(rs.getString(1));
                a.setHashedPassword(rs.getString(2), true);
                a.setEmpNo(rs.getString(3));
                a.setPrivilege(rs.getString(4));
                l.add(a);
            }
            return l;
        };

        DBAccessUtil.QueryResult qr = DBAccessUtil.queryWrapped(sql, rsh);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return ((List<Account>) qr.result).iterator();
    }


    // 测试
    public static void main(String[] args) throws SQLException {
        System.out.println("查询全部：");
        for (Iterator it = getAll(); it.hasNext(); ) {
            Account a = (Account) it.next();
            System.out.println(a);
        }

        Account newAcc =
                new Account("testAccount",
                        "123", false);

        System.out.println("增加+查询：");
        add(newAcc);
        System.out.println(get("testAccount"));

        System.out.println("修改+查询：");
        update("testAccount",
                new Account("testAccount",
                        "233", false));
        System.out.println(get("testAccount"));

        System.out.println("删除+查询全部：");
        delete("testAccount");

        for (Iterator it = getAll(); it.hasNext(); ) {
            Account a = (Account) it.next();
            System.out.println(a);
        }

        Statement stmt =
                MySqlUtil.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM account");
        rs.last();
        System.out.println(rs.getRow());
        System.out.println(rs.getMetaData());
        System.out.println(rs.getMetaData().getColumnCount());
    }
}
