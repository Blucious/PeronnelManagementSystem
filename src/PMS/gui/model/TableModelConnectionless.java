package PMS.gui.model;

import PMS.db.util.DBAccessUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;


public class TableModelConnectionless extends AbstractTableModel {

    private DBAccessUtil.TableModelData tableModelData; // 表数据模型
    private int displayOffset = 0; // 显示偏移

    public void setDisplayOffset(int offset) {
        displayOffset = offset;
    }

    public int getDisplayOffset() {
        return displayOffset;
    }

    public TableModelConnectionless() {
    }

    public TableModelConnectionless(String sql, Object... args) {
        setQuery(sql, args);
    }

    public TableModelConnectionless(int offset, String sql, Object... args) {
        this(sql, args);
        displayOffset = offset;
    }

    public void setQuery(String sql, Object... args) {
        tableModelData = DBAccessUtil.queryTableModelData(sql, args);

        fireTableStructureChanged(); //表中的列数，新列的名称和类型等更改时，调用该方法更新表格
    }

    @Override
    public String getColumnName(int column) {
        return tableModelData.columnNames.get(column + displayOffset);
    }

    @Override
    public int getRowCount() {
        int result = 0;
        if (tableModelData != null) {
            result = tableModelData.valuesList.size();
        }
        return result;
    }

    @Override
    public int getColumnCount() {
        int result = 0;
        if (tableModelData != null) {
            result = tableModelData.columnNames.size();
            // 不显示偏移，所以将列数减去偏移值
            result -= displayOffset;
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // 父类获取的列数是减去偏移后的，所以这里要加上偏移
        return tableModelData.valuesList.get(rowIndex).get(displayOffset + columnIndex);
    }

    public Object getOriginValueAt(int rowIndex, int columnIndex) {
        return tableModelData.valuesList.get(rowIndex).get(columnIndex);
    }

    public static void main(String[] args) {

        //1.创建表格，将查询的所有记录显示在表格中
        TableModelConnectionless tableModel =
                new TableModelConnectionless(1, "select * from account");//由数据库表question创建表格模型
        JTable resultTable = new JTable(tableModel);//表格：显示数据库表question中的所有记录
        JScrollPane scrollPanetable = new JScrollPane(resultTable);//需要是显示滚动条

        //2.得到表的所有列名称---数据库表的字段
        JFrame fr = new JFrame("显示表格");
        fr.getContentPane().add(scrollPanetable, BorderLayout.CENTER);
        fr.setSize(400, 120);
        fr.setVisible(true);
    }
}
