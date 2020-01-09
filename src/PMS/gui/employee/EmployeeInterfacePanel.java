/*
 * Created by JFormDesigner on Sat Jan 04 12:36:44 CST 2020
 */

package PMS.gui.employee;


import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import PMS.db.DBClockingIn;
import PMS.entity.Account;
import PMS.gui.model.TableModel;


/**
 * @author she
 */
public class EmployeeInterfacePanel extends JPanel {
    private TableModel tableModelemployee;
    private String empNo;
    private Account account;

    public EmployeeInterfacePanel(Account account) {
        initComponents();
        this.account = account;
        this.empNo = account.getEmpNo();
        tableModelemployee = new TableModel();
        Employeeinfo.setModel(tableModelemployee);
        if (DBClockingIn.get(empNo) != null) {

            refresh(empNo);
        } else {
            String sql = "select empNo as'员工号', empName as'员工名' , empBirthday as'生日', empDepNo as'部门号' ,empTitle as '职称',empClockingIn as'考勤天数' from employee where empNo =?";
            tableModelemployee.setQuery(sql, empNo);


        }

    }

    public void refresh(String empNo) {
        String sql = "SELECT concat(year(Cldatetime), '-', month(Cldatetime))as '考勤月份', empNo as'员工号', empName as'员工名' , empBirthday as'生日', empDepNo as'部门号' ,empTitle as '职称',count(*) as'考勤天数' FROM clockingin,employee where clockingin.Cleno=employee.empNo and empNo=? group by year(Cldatetime),month(Cldatetime)";

//            String sql = "SELECT concat(year(Cldatetime), '-', month(Cldatetime))as '考勤月份', empNo as'员工号', empName as'员工名' , empBirthday as'生日', empDepNo as'部门号' ,empTitle as '职称',count(*) as'考勤天数' FROM clockingin,employee where clockingin.Cleno=employee.empNo and empNo=? group by year(Cldatetime),month(Cldatetime)";
//            tableModelemployee = new TableModel(sql, empNo);
        tableModelemployee.setQuery(sql, empNo);
    }

    private void EmployeeinfoMouseReleased(MouseEvent e) throws SQLException {
        int line = Employeeinfo.getSelectedRow();
        String datetime = (String) Employeeinfo.getValueAt(line, 0);
        EmployeeClockingin employeeClockingin = new EmployeeClockingin(null, empNo, datetime, account);
        employeeClockingin.setVisible(true);
        employeeClockingin.dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - she
        scrollPane1 = new JScrollPane();
        Employeeinfo = new JTable();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
                .border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder
                .CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.
                awt.Font.BOLD, 12), java.awt.Color.red), getBorder()))
        ;
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e
            ) {
                if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
            }
        })
        ;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== scrollPane1 ========
        {

            //---- Employeeinfo ----
            Employeeinfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        EmployeeinfoMouseReleased(e);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            scrollPane1.setViewportView(Employeeinfo);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - she
    private JScrollPane scrollPane1;
    private JTable Employeeinfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
