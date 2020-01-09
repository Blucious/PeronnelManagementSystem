/*
 * Created by JFormDesigner on Sun Jan 05 18:22:07 CST 2020
 */

package PMS.gui.message;

import java.awt.event.*;

import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.entity.Privilege;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.TableModelConnectionless;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * @author c
 */
public class SendMessageDialog extends DataInputDialog {

    private Account accCurr;
    private TableModelConnectionless tableModelAccountSelection;
    private String privilegeLimit;

    public SendMessageDialog(Account accountCurrent) {
        this(accountCurrent, null);
    }

    /**
     * @param accountCurrent 发送人账户
     * @param accountReceiver  接收人
     */
    public SendMessageDialog(Account accountCurrent, Account accountReceiver) {
        initComponents();

        // 设置当前账户
        accCurr = accountCurrent;

        // 设置发送人限制
        // 普通员工只能发消息给人事员工，人事员工也只能给普通员工法消息
        if (accCurr.isPersonnelStaff()) {
            privilegeLimit = Privilege.PRIVILEGE_NORMAL_STAFF;
        } else if (accCurr.isNormalStaff()) {
            privilegeLimit = Privilege.PRIVILEGE_PERSONNEL_STAFF;
        } else {
            privilegeLimit = "";
        }

        // 初始化控件
        tableModelAccountSelection = new TableModelConnectionless();
        tableAccountSelection.setModel(tableModelAccountSelection);
        if (accountReceiver != null) {
            searchEmployeeByAccName(accountReceiver.getName());
            tableAccountSelection.setRowSelectionInterval(0, 0);
        } else {
            searchEmployeeByName("%");
        }
    }

    private void searchEmployeeByAccName(String accName) {
        tableModelAccountSelection.setQuery("SELECT empName 员工姓名, empNo 员工号, accName 账户名 " +
                        "FROM account JOIN employee ON (accEmpNo=empNo) " +
                        "WHERE accPrivilege=? AND accName like ?",
                privilegeLimit, accName);
    }

    private void searchEmployeeByName(String empName) {
        tableModelAccountSelection.setQuery("SELECT empName 员工姓名, empNo 员工号, accName 账户名 " +
                        "FROM account JOIN employee ON (accEmpNo=empNo) " +
                        "WHERE accPrivilege=? AND empName like ?",
                privilegeLimit, empName);
    }

    public static class Result {
        List<Account> accReceiverList;
        String message;
    }

    private void okButtonMouseReleased(MouseEvent e) {
        int[] rows = tableAccountSelection.getSelectedRows();
        String message = editorPaneMessage.getText();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "没有选择发送对象", "发送失败", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (message.length() == 0) {
            JOptionPane.showMessageDialog(this,
                    "发送内容不能为空", "发送失败", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Result r = new Result();
        r.accReceiverList = new ArrayList<>(rows.length);
        r.message = message;

        for (int i = 0; i < rows.length; i++) {
            String receiverAccName = (String) tableAccountSelection.getValueAt(rows[i], 2);
            Account receiverAcc = DBAccount.get(receiverAccName);
            r.accReceiverList.add(receiverAcc);
        }

        setInputData(r);

        setVisible(false);
    }

    private void cancelButtonMouseReleased(MouseEvent e) {
        setVisible(false);
    }

    private void buttonSearchMouseClicked(MouseEvent e) {
        String empName = textFieldReceiver.getText();
        searchEmployeeByName(empName);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textFieldReceiver = new JTextField();
        buttonSearch = new JButton();
        scrollPane2 = new JScrollPane();
        tableAccountSelection = new JTable();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        editorPaneMessage = new JEditorPane();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("\u53d1\u9001\u6d88\u606f");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 123, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

                //---- label1 ----
                label1.setText("\u641c\u7d22");
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(textFieldReceiver, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- buttonSearch ----
                buttonSearch.setText("\u67e5\u627e\u59d3\u540d\uff08\u901a\u914d\u7b26'%'\u3001'_'\uff09");
                buttonSearch.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonSearchMouseClicked(e);
                    }
                });
                contentPanel.add(buttonSearch, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(tableAccountSelection);
                }
                contentPanel.add(scrollPane2, new GridBagConstraints(1, 1, 6, 3, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setText("\u6d88\u606f\u5185\u5bb9\uff1a");
                contentPanel.add(label2, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(editorPaneMessage);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(1, 4, 6, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u53d1\u9001");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        cancelButtonMouseReleased(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(515, 455);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textFieldReceiver;
    private JButton buttonSearch;
    private JScrollPane scrollPane2;
    private JTable tableAccountSelection;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JEditorPane editorPaneMessage;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
