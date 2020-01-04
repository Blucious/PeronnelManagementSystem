package PMS.gui.model;

import PMS.db.util.MySqlUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.*;

/**
 * 1.MyTableModel类是自定义表格模型类，用于创建不同表格模型的表格
 * 2.MyTableModel类是AbstractTableModel 的子类，只需提供对以下三个方法的实现：
 * public int getRowCount();
 * public int getColumnCount();
 * public Object getValueAt(int row, int column);
 */

public class TableModel extends AbstractTableModel {

    private PreparedStatement preparedStatement;    // 语句对象
    private ResultSet resultSet;                    // 结果集：通过执行查询数据库的语句得到
    private ResultSetMetaData resultSetMetaData;    // 结果集元数据：ResultSet对象中列的类型和属性信息的对象。
    private int numberOfRows;                       // 表格模型中的行数
    private int numberOfColumns;                    // 表格模型中的列数

    public TableModel(String sql, Object... args) {
        // 初始化结果集元数据、结果集行数和列数等
        setQuery(sql, args);
    }

    // 覆盖父类AbstractTableModel的方法：设置表格模型的列标题
    public String getColumnName(int column) {
        try {
            return resultSetMetaData.getColumnName(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 必须覆盖父类AbstractTableModel的抽象方法：设置表格模型的列数
    public int getColumnCount() {
        return numberOfColumns;
    }

    // 必须覆盖父类AbstractTableModel的抽象方法：设置表格模型的行数
    public int getRowCount() {
        return numberOfRows;
    }

    // 必须覆盖父类AbstractTableModel的抽象方法：设置单元格的值
    public Object getValueAt(int row, int column) {
        Object result = "";
        try {
            // 参数下标从0开始
            // resultSet下标从1开始
            resultSet.absolute(row + 1); // 游标定位到指定的行
            result = resultSet.getObject(column + 1); //获取该行指定列的值
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public void setQueryWithArray(String sql, Object[] args) {
//        setQuery(sql, args,1);
//    }

    // 自定义方法：对参数指定的数据库表进行查询，通过结果集元数据初始化表格的行数和列数等
    public void setQuery(String sql, Object... args) {

        // 先关闭之前的数据库结果集和语句对象，以免内存泄漏
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ignored) {
            }
        }

        try {
            //查询结果集
            preparedStatement = MySqlUtil.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();

            //将指针移动到此 ResultSet 对象的最后一行。以获取当前行的编号，也就是记录数量（没有记录则为0）
            resultSet.last();
            numberOfRows = resultSet.getRow();
            // 获取结果集的元数据，也就是列的编号、类型和属性
            resultSetMetaData = resultSet.getMetaData();
            numberOfColumns = resultSetMetaData.getColumnCount(); //得到ResultSet对象中的列数
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fireTableStructureChanged(); //表中的列数，新列的名称和类型等更改时，调用该方法更新表格
    }


    // 自定义方法：得到构造方法的参数所指定表的所有列名(表的所有字段名)
    public String[] getColumnNames() {
        String[] columnName = null;
        try {
            columnName = new String[numberOfColumns];
            for (int i = 0; i < numberOfColumns; i++) {
                columnName[i] = resultSetMetaData.getColumnName(i + 1); // 表的列编号从1开始
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return columnName; // 返回表的所有字段名
    }

    //测试
    public static void main(String[] args) {
        //1.创建表格，将数据库表question的所有记录显示在表格中
        TableModel tableModel = new TableModel("select * from account");//由数据库表question创建表格模型
        JTable resultTable = new JTable(tableModel);//表格：显示数据库表question中的所有记录
        JScrollPane scrollPanetable = new JScrollPane(resultTable);//需要是显示滚动条
        //2.得到表的所有列名称---数据库表的字段
        StringBuilder result = new StringBuilder("emp表的列名：  ");
        String[] names = tableModel.getColumnNames();
        for (String name : names) {
            result.append(name).append("   ");
        }
        //3.创建窗口并显示表格
        JFrame fr = new JFrame("显示表格");
        fr.getContentPane().add(new JLabel(result.toString()), BorderLayout.NORTH);
        fr.getContentPane().add(scrollPanetable, BorderLayout.CENTER);
        fr.setSize(400, 120);
        fr.setVisible(true);
    }
}
