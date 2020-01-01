/*
 * Created by JFormDesigner on Mon Dec 30 11:11:14 CST 2019
 */

package PMS;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

import PMS.entity.Account;
import PMS.security.PwdUtil;
import PMS.db.DBAccount;
import PMS.op.OPAccount;

/**
 * @author unknown
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    private void buttonLoginMouseReleased(MouseEvent e) {
        String userName = loginTextFieldUserName.getText();
        char[] userPassword = loginPasswordFieldPassword.getPassword();

        // 查询输入的用户名对应的账户
        Account account = DBAccount.getAccount(userName);

        // 比较数字摘要
        if (account != null &&
                PwdUtil.checkPwd(userPassword, account.getHashedPassword())) {
            JOptionPane.showMessageDialog(
                    this, "登录成功");
        } else {
            JOptionPane.showMessageDialog(
                    this, "账户或密码错误");
        }

    }


    private void registerButtonRegisterMouseReleased(MouseEvent e) {
        String userName = registerTextFieldAccount.getText();
        char[] userPassword = registerPasswordFieldPassword.getPassword();
        char[] userPasswordAgain = registerPasswordFieldPasswordAgain.getPassword();

        // 账户名不允许为空
        if (userName.length() > 0) {
            // 两次输入的密码必须相同
            if (Arrays.equals(userPassword, userPasswordAgain)) {
                // 密码评估必须大于50分
                PwdUtil.EvaluationResult er = PwdUtil.evaluatePwd(userPassword);
                if (er.score >= 50) {
                    // 添加新用户
                    Account account = new Account(userName, new String(userPassword), false);
                    boolean state = DBAccount.addAccount(account);

                    // 判断是否添加成功，有可能因为账户存在而添加失败
                    if (state) {
                        JOptionPane.showMessageDialog(
                                this, "注册成功");
                        // 重置密码评估信息
                        registerLabelEvalution.setText("/");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            this, "密码强度未达到标准");
                }
            } else {
                JOptionPane.showMessageDialog(
                        this, "两次输入的密码不同");
            }
        } else {
            JOptionPane.showMessageDialog(
                    this, "账户名不允许为空");
        }
    }

    private void registerPasswordFieldPasswordKeyReleased(KeyEvent e) {
        char[] userPassword = registerPasswordFieldPassword.getPassword();
        PwdUtil.EvaluationResult er = PwdUtil.evaluatePwd(userPassword);
        registerLabelEvalution.setText(er.toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        tabbedPane = new JTabbedPane();
        panelLogin = new JPanel();
        loginPasswordFieldPassword = new JPasswordField();
        loginTextFieldUserName = new JTextField();
        loginLabelAccount = new JLabel();
        loginLabelPassword = new JLabel();
        loginButtonLogin = new JButton();
        panelRegister = new JPanel();
        registerLabelAccount = new JLabel();
        registerTextFieldAccount = new JTextField();
        registerLabelPassword = new JLabel();
        registerPasswordFieldPassword = new JPasswordField();
        registerLabelEvalution = new JLabel();
        registerLabelPasswordAgain = new JLabel();
        registerPasswordFieldPasswordAgain = new JPasswordField();
        registerButtonRegister = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u4eba\u4e8b\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== tabbedPane ========
        {

            //======== panelLogin ========
            {
                panelLogin.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
                javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
                . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
                .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,panelLogin. getBorder( )) ); panelLogin. addPropertyChangeListener (new java. beans.
                PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .
                equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                panelLogin.setLayout(new GridBagLayout());
                ((GridBagLayout)panelLogin.getLayout()).columnWidths = new int[] {4, 4, 150, 0};
                ((GridBagLayout)panelLogin.getLayout()).rowHeights = new int[] {26, 26, 34, 0};
                ((GridBagLayout)panelLogin.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panelLogin.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                panelLogin.add(loginPasswordFieldPassword, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 3, 0), 0, 0));
                panelLogin.add(loginTextFieldUserName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 3, 0), 0, 0));

                //---- loginLabelAccount ----
                loginLabelAccount.setText("\u8d26\u6237\uff1a");
                panelLogin.add(loginLabelAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- loginLabelPassword ----
                loginLabelPassword.setText("\u5bc6\u7801\uff1a");
                panelLogin.add(loginLabelPassword, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 3, 3), 0, 0));

                //---- loginButtonLogin ----
                loginButtonLogin.setText("\u767b\u5f55");
                loginButtonLogin.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        buttonLoginMouseReleased(e);
                    }
                });
                panelLogin.add(loginButtonLogin, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            tabbedPane.addTab("\u767b\u5f55", panelLogin);
            tabbedPane.setBackgroundAt(0, Color.lightGray);

            //======== panelRegister ========
            {
                panelRegister.setLayout(new GridBagLayout());
                ((GridBagLayout)panelRegister.getLayout()).columnWidths = new int[] {70, 0, 153, 0, 0};
                ((GridBagLayout)panelRegister.getLayout()).rowHeights = new int[] {0, 0, 0, 7, 0};
                ((GridBagLayout)panelRegister.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panelRegister.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- registerLabelAccount ----
                registerLabelAccount.setText("\u8d26\u6237\uff1a");
                panelRegister.add(registerLabelAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 4, 3), 0, 0));
                panelRegister.add(registerTextFieldAccount, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 4, 3), 0, 0));

                //---- registerLabelPassword ----
                registerLabelPassword.setText("\u5bc6\u7801\uff1a");
                panelRegister.add(registerLabelPassword, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 4, 3), 0, 0));

                //---- registerPasswordFieldPassword ----
                registerPasswordFieldPassword.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        registerPasswordFieldPasswordKeyReleased(e);
                    }
                });
                panelRegister.add(registerPasswordFieldPassword, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 4, 3), 0, 0));

                //---- registerLabelEvalution ----
                registerLabelEvalution.setText("/");
                panelRegister.add(registerLabelEvalution, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 4, 0), 0, 0));

                //---- registerLabelPasswordAgain ----
                registerLabelPasswordAgain.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
                registerLabelPasswordAgain.setMinimumSize(new Dimension(60, 17));
                panelRegister.add(registerLabelPasswordAgain, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 4, 3), 0, 0));
                panelRegister.add(registerPasswordFieldPasswordAgain, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 4, 3), 0, 0));

                //---- registerButtonRegister ----
                registerButtonRegister.setText("\u6ce8\u518c");
                registerButtonRegister.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        registerButtonRegisterMouseReleased(e);
                    }
                });
                panelRegister.add(registerButtonRegister, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 3), 0, 0));
            }
            tabbedPane.addTab("\u6ce8\u518c", panelRegister);
        }
        contentPane.add(tabbedPane);
        setSize(365, 265);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JTabbedPane tabbedPane;
    private JPanel panelLogin;
    private JPasswordField loginPasswordFieldPassword;
    private JTextField loginTextFieldUserName;
    private JLabel loginLabelAccount;
    private JLabel loginLabelPassword;
    private JButton loginButtonLogin;
    private JPanel panelRegister;
    private JLabel registerLabelAccount;
    private JTextField registerTextFieldAccount;
    private JLabel registerLabelPassword;
    private JPasswordField registerPasswordFieldPassword;
    private JLabel registerLabelEvalution;
    private JLabel registerLabelPasswordAgain;
    private JPasswordField registerPasswordFieldPasswordAgain;
    private JButton registerButtonRegister;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    //测试LoginFrame类
    public static void main(String[] args) {
        JFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}
