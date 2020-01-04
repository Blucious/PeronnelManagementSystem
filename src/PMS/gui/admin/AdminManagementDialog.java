/*
 * Created by JFormDesigner on Fri Jan 03 18:14:58 CST 2020
 */

package PMS.gui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.TableModel;
import PMS.gui.account.ModifyAccountDialog;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class AdminManagementDialog extends DataInputDialog {
    Account account;
    private TableModel tableModelAccount;

    public AdminManagementDialog(Window owner, Account account) {
        super(owner);
        initComponents();

        this.account = account;

        tableModelAccount = new TableModel("SELECT * FROM account");
        tableAccount.setModel(tableModelAccount);
    }

    private void button1MouseReleased(MouseEvent e) {
        String sql = "SELECT * FROM ACCOUNT WHERE accName=?";
        tableModelAccount.setQuery(sql, textFieldAccountName.getText());
    }

    private void tableAccountMouseReleased(MouseEvent e) {
        int row = tableAccount.getSelectedRow();
        ModifyAccountDialog d = new ModifyAccountDialog(
                null, (String) tableAccount.getValueAt(row, 0), this.account);
        d.setVisible(true);
        d.dispose();

        Account account = (Account) d.getInputData();
        if (account != null) {
//            System.out.println(account);
            DBAccount.update(account.getName(), account);
            tableModelAccount = new TableModel("SELECT * FROM account");
            tableAccount.setModel(tableModelAccount);

        }

    }

    private void scrollPane2MouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void cancelButtonMouseReleased(MouseEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
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
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                    swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border
                    .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog"
                    , java.awt.Font.BOLD, 12), java.awt.Color.red), dialogPane.getBorder
                    ()));
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java
                                                   .beans.PropertyChangeEvent e) {
                    if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException
                            ();
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
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
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
