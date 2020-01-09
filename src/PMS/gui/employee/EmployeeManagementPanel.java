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
import PMS.db.DBClockingIn;
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

    private TableModel tableModelEmployee; // 表数据模型
    private Account account;

    public EmployeeManagementPanel(Account account) {
        initComponents();


        this.account = account;

        tableModelEmployee = new TableModel();
        tableEmployee.setModel(tableModelEmployee);
        refreshTable();
    }

    private void refreshTable() {
        String sql = "select empNo 员工号, empName 员工名, empBirthday 出生日期," +
                "depName 部门, empTitle 职称, count(Cleno) 考勤天数 " +
                "from employee left outer join clockingin on (employee.empNo=clockingin.Cleno) " +
                "left outer join department on (employee.empDepNo=department.depNo) " +
                "group by empNo";
        tableModelEmployee.setQuery(sql);
    }

    private void buttonModifyMouseReleased(MouseEvent e) {
        int selectedRow = tableEmployee.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this, "未选择员工", "提示",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String empOriginalNo = (String) tableEmployee.getValueAt(selectedRow, 0);
        Employee empOriginal = DBEmployee.get(empOriginalNo);


        DataInputDialog dialog = new ModifyEmployeeDialog(empOriginal);
        dialog.setVisible(true);
        dialog.dispose();

        Employee empModified = (Employee) dialog.getInputData();
        if (empModified != null) {
            DBEmployee.update(empOriginal.getNo(), empModified);
            refreshTable();
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
            try {

                PreparedStatement ps = MySqlUtil.prepareStatement(sql);
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    DBAccount.delete(resultSet.getString(1));
                }
                DBClockingIn.delete(deleno[i]);
                DBEmployee.delete(deleno[i]);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        refreshTable();
    }

    private void tableEmployeeMouseReleased(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int selectedRow = tableEmployee.getSelectedRow();
            String tableeno = (String) tableEmployee.getValueAt(selectedRow, 0);
            EmployeeClockingin employeeClockingin = new EmployeeClockingin(null, tableeno, account);
            employeeClockingin.setVisible(true);
            employeeClockingin.dispose();
        }
    }

    private void buttonModifyClockingInMouseReleased(MouseEvent e) {
        SetClockinginTime setClockinginTime = new SetClockinginTime(null);
        setClockinginTime.setVisible(true);
        setClockinginTime.dispose();
    }

    private void buttonAddMouseReleased(MouseEvent e) {
        DataInputDialog dialog = new AddingEmployeeDialog(null);
        dialog.setVisible(true);
        dialog.dispose();

        Employee emp = (Employee) dialog.getInputData();
//        System.out.println(emp);
        if (emp != null) {

            DBEmployee.add(emp);
            String defaultpwd = emp.getBirthday().toString().replace("-", "");
            Account account = new Account(emp.getName(), defaultpwd, false, emp.getNo(), "普通员工");
            DBAccount.add(account);

            refreshTable();
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
        buttonModifyClockingIn = new JButton();
        scrollPane = new JScrollPane();
        tableEmployee = new JTable();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                .TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt
                .Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void
            propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException()
                        ;
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
                ((GridBagLayout) panel2.getLayout()).columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout) panel2.getLayout()).rowHeights = new int[]{10, 0, 5, 0};
                ((GridBagLayout) panel2.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout) panel2.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0E-4};

                //---- buttonAdd ----
                buttonAdd.setText("\u6dfb\u52a0");
                buttonAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        buttonAddMouseReleased(e);
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

                //---- buttonModifyClockingIn ----
                buttonModifyClockingIn.setText("\u4fee\u6539\u8003\u52e4\u65f6\u95f4");
                buttonModifyClockingIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        buttonModifyClockingInMouseReleased(e);
                    }
                });
                panel2.add(buttonModifyClockingIn, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
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
                tableEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
    private JButton buttonModifyClockingIn;
    private JScrollPane scrollPane;
    private JTable tableEmployee;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
