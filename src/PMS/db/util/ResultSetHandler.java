package PMS.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集处理接口
 * 用于在执行查询时将结果集转换为想要的对象
 */
public interface ResultSetHandler {
    public Object doHandler(ResultSet rs) throws SQLException;
}
