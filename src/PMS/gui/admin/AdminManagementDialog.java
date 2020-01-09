/*
 * Created by JFormDesigner on Fri Jan 03 18:14:58 CST 2020
 */

package PMS.gui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.client.Client;
import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.gui.LoginFrame;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.TableModel;
import PMS.gui.account.ModifyAccountDialog;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class AdminManagementDialog extends DataInputDialog {
    private Account account;
    private Client client;
    private TableModel tableModelAccount;

    public AdminManagementDialog(Window owner, Account account) {
        super(owner);
        initComponents();

        this.account = account;

        tableModelAccount = new TableModel("SELECT * FROM account");
        tableAccount.setModel(tableModelAccount);
    }

    private void button1MouseReleased(MouseEvent e) {
        String sql = "SELECT * FROM ACCOUNT WHERE accName like concat('%',?,'%')";
        tableModelAccount.setQuery(sql, textFieldAccountName.getText());
    }

    private void tableAccountMouseReleased(MouseEvent e) {
        int row = tableAccount.getSelectedRow();
        ModifyAccountDialog d = new ModifyAccountDialog(
                null, (String) tableAccount.getValueAt(row, 0), this.account);
        d.setVisible(true);
        d.dispose();
        if (d.getInputData() instanceof Account) {
            System.out.println(d.getInputData() instanceof Account);
            Account account = (Account) d.getInputData();
            if (account != null) {
//            System.out.println(account);
                DBAccount.update(account.getName(), account);

                tableModelAccount.setQuery("SELECT * FROM account");

            }

        } else {
            DBAccount.delete(String.valueOf(d.getInputData()));

            tableModelAccount.setQuery("SELECT * FROM account");
        }

    }

    private void scrollPane2MouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void cancelButtonMouseReleased(MouseEvent e) {
        setVisible(false);
    }

    private void tableaccountMouseReleased(MouseEvent e) {
        // TODO add your code here
    }

//    private void menuItem2MouseReleased(MouseEvent e) {
//        ModifyAccountDialog modifyAccount =
//                new ModifyAccountDialog(null, account.getName(),account);
//        modifyAccount.setVisible(true);
//        modifyAccount.dispose();
//
//        Account account = (Account) modifyAccount.getInputData();
//        if (account != null) {
//            System.out.println(account); // 调试
//            DBAccount.update(account.getName(), account);
//        }
//    }

    private void menuItem3MouseReleased(MouseEvent e) {
        this.setVisible(false);
        this.dispose();
        Frame f = new LoginFrame();
        f.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem3 = new JMenuItem();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelAccountName = new JLabel();
        textFieldAccountName = new JTextField();
        buttonQuery = new JButton();
        scrollPane = new JScrollPane();
        tableAccount = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u8d26\u53f7");

                //---- menuItem1 ----
                menuItem1.setText("\u4fee\u6539\u5bc6\u7801");
                menu1.add(menuItem1);

                //---- menuItem3 ----
                menuItem3.setText("\u9000\u51fa\u767b\u5f55");
                menuItem3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItem3MouseReleased(e);
                    }
                });
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                    (0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                    .TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12), java.awt
                    .Color.red), dialogPane.getBorder()));
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void
                propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException()
                            ;
                }
            });
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
                                "[82,fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- labelAccountName ----
                labelAccountName.setText("\u8d26\u6237\u540d\uff1a");
                contentPanel.add(labelAccountName, "cell 1 1");
                contentPanel.add(textFieldAccountName, "cell 3 1 3 1");

                //---- buttonQuery ----
                buttonQuery.setText("\u67e5\u8be2");
                buttonQuery.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        button1MouseReleased(e);
                    }
                });
                contentPanel.add(buttonQuery, "cell 6 1");

                //======== scrollPane ========
                {
                    scrollPane.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            scrollPane2MouseReleased(e);
                        }
                    });

                    //---- tableAccount ----
                    tableAccount.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            tableaccountMouseReleased(e);
                            tableAccountMouseReleased(e);
                        }
                    });
                    scrollPane.setViewportView(tableAccount);
                }
                contentPanel.add(scrollPane, "cell 0 3 9 1");
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
                        cancelButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem3;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelAccountName;
    private JTextField textFieldAccountName;
    private JButton buttonQuery;
    private JScrollPane scrollPane;
    private JTable tableAccount;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
//    public static void main(String [] args){
//        Adminmangement test = new Adminmangement(null);
//        test.setVisible(true);
//    }
}
