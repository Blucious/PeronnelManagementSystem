package PMS.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集处理接口
 * 用于在执行查询时设置结构集
 */
public interface ResultSetHandler {
    public Object doHandler(ResultSet rs) throws SQLException;
}
