/*
 * Created by JFormDesigner on Mon Dec 30 11:11:14 CST 2019
 */

package PMS;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.security.PwdUtil;
import PMS.db.DBAccount;

/**
 * @author unknown
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    private void buttonLoginMouseReleased(MouseEvent e) {
        String userName = textFieldUserName.getText();
        char[] userPassword = passwordFieldPassword.getPassword();

        String hashedPassword = DBAccount.getHashedPassword(userName);
        if (hashedPassword != null) {
            if (PwdUtil.checkPwd(userPassword, hashedPassword)) {
                JOptionPane.showMessageDialog(
                        this, "µ«¬º≥…π¶");
            } else {
                JOptionPane.showMessageDialog(
                        this, "µ«¬º ß∞‹£¨’ÀªßªÚ√‹¬Î¥ÌŒÛ");
            }
        } else {
            JOptionPane.showMessageDialog(
                    this, "≤È—Ø ß∞‹");
        }

    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
        // TODO add your code here
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        labelAccount = new JLabel();
        textFieldUserName = new JTextField();
        labelPassword = new JLabel();
        passwordFieldPassword = new JPasswordField();
        buttonLogin = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u4eba\u4e8b\u7ba1\u7406\u7cfb\u7edf");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {54, 56, 256, 51, 86, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {31, 40, 0, 67, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 0.0, 0.0, 1.0E-4};

        //---- labelAccount ----
        labelAccount.setText("\u8d26\u6237\uff1a");
        contentPane.add(labelAccount, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(textFieldUserName, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- labelPassword ----
        labelPassword.setText("\u5bc6\u7801\uff1a");
        contentPane.add(labelPassword, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(passwordFieldPassword, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- buttonLogin ----
        buttonLogin.setText("\u767b\u5f55");
        buttonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
                button1MouseClicked(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                buttonLoginMouseReleased(e);
            }
        });
        contentPane.add(buttonLogin, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
        setSize(355, 230);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JLabel labelAccount;
    private JTextField textFieldUserName;
    private JLabel labelPassword;
    private JPasswordField passwordFieldPassword;
    private JButton buttonLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    //≤‚ ‘LoginFrame¿‡
    public static void main(String[] args) {
        JFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}
