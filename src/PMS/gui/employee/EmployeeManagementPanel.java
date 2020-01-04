/*
 * Created by JFormDesigner on Wed Jan 01 21:17:27 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import PMS.db.DBAccount;
import PMS.db.DBEmployee;
import PMS.db.util.MySqlUtil;
import PMS.entity.Account;
import PMS.entity.Employee;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.TableModel;

/**
 * @author c
 */
public class EmployeeManagementPanel extends JPanel {
    // 表数据模型
    private TableModel tableModelEmployee;

    public EmployeeManagementPanel() {
        initComponents();
        String sql = "select * from employee ";
        tableModelEmployee = new TableModel(sql);
        tableEmployee.setModel(tableModelEmployee);


    }

    private void button1MouseReleased(MouseEvent e) {
        DataInputDialog dialog = new AddingEmployeeDialog(null);
        dialog.setVisible(true);
        dialog.dispose();

        Employee emp = (Employee) dialog.getInputData();
        if (emp != null) {

            DBEmployee.add(emp);
            String defaultpwd = emp.getBirthday().toString().replace("-", "");
            Account account = new Account(emp.getName(), defaultpwd, false, emp.getNo(), "普通员工");
            DBAccount.add(account);
            tableModelEmployee.setQuery("select * from employee");
        }
    }

    private void buttonModifyMouseReleased(MouseEvent e) {
        DataInputDialog dialog = new ModifyEmployeeDialog(null);
        dialog.setVisible(true);
        dialog.dispose();

        Employee emp = (Employee) dialog.getInputData();
        if (emp != null) {
            DBEmployee.update(emp.getNo(), emp);
            tableModelEmployee.setQuery("select * from employee");
        }
    }

    private void buttonDeleteMouseReleased(MouseEvent e) {
        DataInputDialog deleteEmployee = new DeleteEmployeeDialog(null);
        deleteEmployee.setVisible(true);
        deleteEmployee.dispose();
        String[] deleno = String.valueOf(deleteEmployee.getInputData()).split(";");
        ResultSet resultSet = null;
        for (int i = 0; i < deleno.length; i++) {
            String sql = "select accName from account where accEmpNo in (select empNo from employee where empNo ='" + deleno[i] + "')";
//            DBAccessUtil.query(sql,);
            try {

                PreparedStatement ps = MySqlUtil.prepareStatement(sql);
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                    DBAccount.delete(resultSet.getString(1));
                }

                DBEmployee.delete(deleno[i]);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        tableModelEmployee.setQuery("select * from employee");
    }

    private void tableEmployeeMouseReleased(MouseEvent e) {

//        tableModelEmployee.setQuery("select * from employee");
//
        int line = tableEmployee.getSelectedRow();
        ModifyEmployeeDialog modifyEmployee = new ModifyEmployeeDialog(null, (String) tableEmployee.getValueAt(line, 0));
        modifyEmployee.setVisible(true);

        modifyEmployee.dispose();

        Employee emp = (Employee) modifyEmployee.getInputData();
        if (emp != null) {
            DBEmployee.update(emp.getNo(), emp);
            tableModelEmployee.setQuery("select * from employee");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        panel1 = new JPanel();
        panel2 = new JPanel();
        buttonAdd = new JButton();
        buttonModify = new JButton();
        buttonDelete = new JButton();
        scrollPane = new JScrollPane();
        tableEmployee = new JTable();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing
                .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
        ), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("bord\u0065r".equals(e.getPropertyName(
                ))) throw new RuntimeException();
            }
        });
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            //======== panel2 ========
            {
                panel2.setMaximumSize(null);
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout) panel2.getLayout()).columnWidths = new int[]{10, 0, 0, 0, 0, 115, 0};
                ((GridBagLayout) panel2.getLayout()).rowHeights = new int[]{10, 0, 5, 0};
                ((GridBagLayout) panel2.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout) panel2.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};

                //---- buttonAdd ----
                buttonAdd.setText("\u6dfb\u52a0");
                buttonAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        button1MouseReleased(e);
                    }
                });
                panel2.add(buttonAdd, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- buttonModify ----
                buttonModify.setText("\u4fee\u6539");
                buttonModify.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        buttonModifyMouseReleased(e);
                    }
                });
                panel2.add(buttonModify, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- buttonDelete ----
                buttonDelete.setText("\u5220\u9664");
                buttonDelete.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        buttonDeleteMouseReleased(e);
                    }
                });
                panel2.add(buttonDelete, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
            }
            panel1.add(panel2);

            //======== scrollPane ========
            {
                scrollPane.setMaximumSize(null);
                scrollPane.setPreferredSize(null);

                //---- tableEmployee ----
                tableEmployee.setMaximumSize(null);
                tableEmployee.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        tableEmployeeMouseReleased(e);
                    }
                });
                scrollPane.setViewportView(tableEmployee);
            }
            panel1.add(scrollPane);
        }
        add(panel1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel panel1;
    private JPanel panel2;
    private JButton buttonAdd;
    private JButton buttonModify;
    private JButton buttonDelete;
    private JScrollPane scrollPane;
    private JTable tableEmployee;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
