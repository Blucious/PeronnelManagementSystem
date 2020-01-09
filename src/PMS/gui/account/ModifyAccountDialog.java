/*
 * Created by JFormDesigner on Fri Jan 03 21:19:03 CST 2020
 */

package PMS.gui.account;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;

import PMS.db.DBPrivilege;
import PMS.entity.Account;
import PMS.entity.Privilege;
import PMS.gui.com.DataInputDialog;

import net.miginfocom.swing.*;

/**
 * @author she
 */
public class ModifyAccountDialog extends DataInputDialog {
    private Account accountToModify;

    public ModifyAccountDialog(Window owner, String info, Account acc) {
        super(owner);
        initComponents();

        accountToModify = acc;

        // 填充固定信息
        textFieldName.setText(info);

        // 设置选项
        Iterator<Privilege> privilegeIterator = DBPrivilege.getAll();
        while (privilegeIterator.hasNext()) {
            Privilege p = privilegeIterator.next();
            comboxPrivilege.addItem(p.getName());
        }

        // 根据权限隐藏一些功能
        if (!accountToModify.isAdmin()) {
            textFieldNo.setVisible(false);
            comboxPrivilege.setVisible(false);
            labelNo.setVisible(false);
            labelPrivilege.setVisible(false);
            scrollPane1.setVisible(false);
            deletebutton.setVisible(false);
        }
    }

    private void comboxprivilegeMouseReleased(MouseEvent e) {

    }

    private void okButtonMouseReleased(MouseEvent e) {




        Account acc;
        if (accountToModify.isAdmin()) {
            acc = new Account(
                    textFieldName.getText(),
                    textFieldPassword.getText(), false,
                    textFieldNo.getText(),
                    ((String)comboxPrivilege.getSelectedItem()));
            if (textFieldNo.getText().length()==0){
                acc.setEmpNo(null);

            }
            this.setInputData(acc);
            System.out.println(acc);
            this.setVisible(false);
        } else {
            acc = new Account(
                    accountToModify.getName(),
                    textFieldPassword.getText(), false,
                    accountToModify.getEmpNo(),
                    accountToModify.getPrivilege());
            this.setInputData(acc);
            System.out.println(acc);
            this.setVisible(false);
        }
    }

    private void cancelButtonMouseReleased(MouseEvent e) {
        this.setVisible(false);
    }

    private void okButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
    }


    private void deletebuttonMouseClicked(MouseEvent e) {
            String accname=textFieldName.getText();

        if (accountToModify.isAdmin()) {
            this.setInputData(accname);
            System.out.println(accname);
            this.setVisible(false);
        }

}

private void button1MouseClicked(MouseEvent e) {
    // TODO add your code here
}


    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - she
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        labelName = new JLabel();
        labelPassword = new JLabel();
        labelNo = new JLabel();
        labelPrivilege = new JLabel();
        labelPrompt = new JLabel();
        textFieldName = new JTextField();
        textFieldPassword = new JTextField();
        textFieldNo = new JTextField();
        scrollPane1 = new JScrollPane();
        comboxPrivilege = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        deletebutton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)contentPanel2.getLayout()).columnWidths = new int[] {0, 319};
                    ((GridBagLayout)contentPanel2.getLayout()).columnWeights = new double[] {1.0, 1.0};

                    //---- labelName ----
                    labelName.setText("\u8d26\u6237\u540d\uff1a");
                    contentPanel2.add(labelName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- labelPassword ----
                    labelPassword.setText("\u5bc6\u7801\uff1a");
                    contentPanel2.add(labelPassword, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- labelNo ----
                    labelNo.setText("\u5458\u5de5\u53f7\uff1a");
                    contentPanel2.add(labelNo, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- labelPrivilege ----
                    labelPrivilege.setText("\u8d26\u6237\u6743\u9650\uff1a");
                    contentPanel2.add(labelPrivilege, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- labelPrompt ----
                    labelPrompt.setText("\u4fee\u6539\u8d26\u53f7\u4fe1\u606f");
                    contentPanel2.add(labelPrompt, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- textFieldName ----
                    textFieldName.setEditable(false);
                    contentPanel2.add(textFieldName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                    contentPanel2.add(textFieldPassword, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                    contentPanel2.add(textFieldNo, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //======== scrollPane1 ========
                    {

                        //---- comboxPrivilege ----
                        comboxPrivilege.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                comboxprivilegeMouseReleased(e);
                            }
                        });
                        scrollPane1.setViewportView(comboxPrivilege);
                    }
                    contentPanel2.add(scrollPane1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                contentPanel.add(contentPanel2, "cell 0 0 3 3");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");

                //---- deletebutton ----
                deletebutton.setText("\u5220\u9664");
                deletebutton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                        deletebuttonMouseClicked(e);
                    }
                });
                buttonBar.add(deletebutton, "cell 1 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        cancelButtonMouseReleased(e);
                    }
                });
                buttonBar.add(cancelButton, "cell 2 0");
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
    private JPanel contentPanel2;
    private JLabel labelName;
    private JLabel labelPassword;
    private JLabel labelNo;
    private JLabel labelPrivilege;
    private JLabel labelPrompt;
    private JTextField textFieldName;
    private JTextField textFieldPassword;
    private JTextField textFieldNo;
    private JScrollPane scrollPane1;
    private JComboBox<String> comboxPrivilege;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton deletebutton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
//    public static void main(String []args){
//        ModifyAccount modifyAccount= new ModifyAccount(null);
//        modifyAccount.setVisible(true);
//    }
}
