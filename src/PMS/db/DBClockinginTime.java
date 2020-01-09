package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.MySqlUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class DBClockinginTime {
    public static boolean update(Time time) {
        String sql = "UPDATE ClockingInTime " +
                "SET ClockingInTime =?" +
                "WHERE ClockingInTime=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        time, get());
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    public static Time get() {
        ResultSet resultSet;
        Time time = null;
        String sql = "select * from ClockingInTime ";
        try {
            PreparedStatement preparedStatement = MySqlUtil.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                time = resultSet.getTime(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return time;

    }

    public static boolean add(Time time) {
        String sql = "insert into ClockingInTime values(?)";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        time);
        if (ur.exception != null) {
            // 1062 Duplicate entry 'stu1' for key 'PRIMARY'
            if (ur.exception.getErrorCode() != 1062) {
                ur.exception.printStackTrace();
            }
        }

        return ur.state;
    }
}


