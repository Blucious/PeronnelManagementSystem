/*
 * Created by JFormDesigner on Mon Jan 06 16:11:04 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;

import javax.swing.*;

import PMS.db.DBClockingIn;
import PMS.entity.Account;
import PMS.entity.ClockingIn;
import PMS.gui.model.TableModel;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class EmployeeClockingin extends JDialog {
    private String eno;
    private Account account;
//    private String datetime;
    private TableModel tableModelclockingin;
    public EmployeeClockingin(Window owner, String eno, String datetime,Account account) throws SQLException {
        super(owner);
        initComponents();
        this.eno=eno;
        this.account=account;
//        this.datetime=datetime;
        String month=datetime.substring(5,6);
        String year=datetime.substring(0,4);
        String sql ="select Cleno as '员工号',Cldatetime as '打卡时间',Clstatus as '考勤状态' from Clockingin where Cleno=? and month(Cldatetime)=? and year(Cldatetime)=?";

        tableModelclockingin = new TableModel(sql,eno,month,year);
        tableClockingin.setModel(tableModelclockingin);

    }
    public EmployeeClockingin(Window owner, String eno ,Account account) {
        super(owner);
        initComponents();

        this.eno=eno;
        this.account=account;
        String sql ="select Cleno as '员工号',Cldatetime as '打卡时间',Clstatus as '考勤状态' from Clockingin where Cleno=?";
        tableModelclockingin = new TableModel(sql,eno);
        tableClockingin.setModel(tableModelclockingin);

    }

    private void scrollPane1MouseReleased(MouseEvent e) {

    }

    private void tableClockinginMouseReleased(MouseEvent e) {
        int line = tableClockingin.getSelectedRow();
        String datetime=String.valueOf(tableClockingin.getValueAt(line, 1));

        if (account.isPersonnelStaff()){
            ModifyClockingin modifyClockingin = new ModifyClockingin(null,datetime,eno);
            modifyClockingin.setVisible(true);
            modifyClockingin.dispose();
            if (modifyClockingin.getInputData()!=null){
                ClockingIn clockingIn =(ClockingIn) modifyClockingin.getInputData();
                DBClockingIn.update(clockingIn.getCleno(),clockingIn,clockingIn.getCldatetime());
                String sql ="select Cleno as '员工号',Cldatetime as '打卡时间',Clstatus as '考勤状态' from Clockingin where Cleno=?";
                tableModelclockingin.setQuery(sql,eno);

            }
        }

    }

    private void okButtonMouseReleased(MouseEvent e) {
        this.setVisible(false);
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        this.setVisible(false);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - she
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        tableClockingin = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red),
            dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));

                //======== scrollPane1 ========
                {
                    scrollPane1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            scrollPane1MouseReleased(e);
                        }
                    });

                    //---- tableClockingin ----
                    tableClockingin.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            tableClockinginMouseReleased(e);
                        }
                    });
                    scrollPane1.setViewportView(tableClockingin);
                }
                contentPanel.add(scrollPane1, "cell 0 0 7 3");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - she
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable tableClockingin;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
