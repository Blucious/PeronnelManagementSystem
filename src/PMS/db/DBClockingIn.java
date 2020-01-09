package PMS.db;

import java.sql.Timestamp;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;


import PMS.entity.ClockingIn;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class DBClockingIn {
    private DBClockingIn() {

    }

    public static boolean add(ClockingIn clockingIn) {
        // 查询语句
        String sql = "INSERT INTO ClockingIn " +
                "(Cleno ,Cldatetime ,Clstatus ) " +
                "VALUES (?,?,?)";
        // 执行查询
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        clockingIn.getCleno(),
                        clockingIn.getCldatetime(),
                        clockingIn.getClstatus()
                );
        // 异常处理
        if (ur.exception != null) {
            // 1062 Duplicate entry 'stu1' for key 'PRIMARY'
            if (ur.exception.getErrorCode() != 1062) {
                ur.exception.printStackTrace();
            }
        }

        return ur.state;
    }

    public static boolean delete(String eno, Timestamp cldate) {
        String sql = "DELETE FROM ClockingIn WHERE Cleno=? and Cldatetime=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, eno, cldate);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    public static boolean delete(String eno) {
        String sql = "DELETE FROM ClockingIn WHERE Cleno=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, eno);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    public static boolean update(String eno, ClockingIn clockingIn, Timestamp cldate) {
        String sql = "UPDATE ClockingIn " +
                "SET Cleno =?, Cldatetime =?, " +
                "Clstatus =?" +
                "WHERE Cleno=? and Cldatetime =?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        clockingIn.getCleno(), clockingIn.getCldatetime(),
                        clockingIn.getClstatus(), eno, cldate);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    public static ClockingIn get(String eno) {
        // 查询语句
        String sql = "SELECT * FROM ClockingIn WHERE Cleno=?";
        // 创建结果集处理函数
        ResultSetHandler rsh = (ResultSet rs) -> {
            ClockingIn clockingIn = null;
            if (rs.next()) {
                clockingIn = new ClockingIn();
                clockingIn.setCleno(rs.getString(1));
                clockingIn.setCldatetime(rs.getTimestamp(2));
                clockingIn.setClstatus(rs.getString(3));

            }
            return clockingIn;
        };
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rsh, eno);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return (ClockingIn) qr.result;
    }

    @SuppressWarnings("unchecked cast")
    public static Iterator<ClockingIn> getAll() {
        String sql = "SELECT * FROM ClockingIn";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<ClockingIn> l = new LinkedList<>();
            ClockingIn clockingIn = null;
            while (rs.next()) { // 因为账户名是唯一的，所以只返回一个结果既可
                clockingIn = new ClockingIn();
                clockingIn.setCleno(rs.getString(1));
                clockingIn.setCldatetime(rs.getTimestamp(2));
                clockingIn.setClstatus(rs.getString(3));

                l.add(clockingIn);
            }
            return l;
        };

        DBAccessUtil.QueryResult qr = DBAccessUtil.queryWrapped(sql, rsh);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return ((List<ClockingIn>) qr.result).iterator();
    }


}

