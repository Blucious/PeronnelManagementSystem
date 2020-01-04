/*
 * Created by JFormDesigner on Sat Jan 04 12:36:44 CST 2020
 */

package PMS.gui.employee;


import javax.swing.*;

import PMS.gui.model.TableModel;


/**
 * @author she
 */
public class EmployeeInterfacePanel extends JPanel {
    private TableModel tableModelemployee;

    public EmployeeInterfacePanel(String empNo) {
        initComponents();

        String sql = "SELECT * FROM employee WHERE empNo=?";
        tableModelemployee = new TableModel(sql, empNo);
        Employeeinfo.setModel(tableModelemployee);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        scrollPane1 = new JScrollPane();
        Employeeinfo = new JTable();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
        javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax
        . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
        . awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .
        PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .
        equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(Employeeinfo);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JScrollPane scrollPane1;
    private JTable Employeeinfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
