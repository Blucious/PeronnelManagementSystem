/*
 * Created by JFormDesigner on Mon Dec 30 11:11:14 CST 2019
 */

package PMS.gui;


import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

import PMS.client.Client;
import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.gui.admin.AdminManagementDialog;
import PMS.gui.account.ModifyAccountDialog;
import PMS.op.OPAccount;
import PMS.security.PasswordUtil;
import PMS.security.VerificationCodeUtil;
import PMS.security.EmailUtil;

/**
 * @author unknown
 */
public class LoginFrame extends JFrame {

    private boolean bMailSended;
    private long millsMailSendTime;
    private String verificationCode;
    private Account accWantToChangePassword;


    public LoginFrame() {
        initComponents();

        bMailSended = false;
    }

    private void buttonLoginMouseReleased(MouseEvent e) {
        String userName = loginTextFieldUserName.getText();
        char[] userPassword = loginPasswordFieldPassword.getPassword();

        Account account = OPAccount.loginVerify(userName, userPassword);

        if (account != null) {
            if (account.getEmpNo() == null && !account.isPersonnelStaff() && !account.isAdmin()) {
                JOptionPane.showMessageDialog(this,
                        "请等待管理员给予权限", "登录失败", JOptionPane.ERROR_MESSAGE);
                // 结束事件
                return;
            }


            if (account.isAdmin()) {
                // 启动管理员专有窗口
                AdminManagementDialog d = new AdminManagementDialog(null, account);
                d.setVisible(true);
            } else {

                Client client = new Client(account);
                if (!client.startAndConnect()) {
//                JOptionPane.showMessageDialog(this,
//                        "登录失败，连接服务器失败。", "错误", JOptionPane.ERROR_MESSAGE);
//                // 结束事件
//                return;
                }

                // 启动主窗口
                MainFrame f = new MainFrame(account, client);
                f.setVisible(true);
            }
            // 关闭自身
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "账户或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void registerButtonRegisterMouseReleased(MouseEvent e) {
        String userName = registerTextFieldAccount.getText();
        char[] userPassword = registerPasswordFieldPassword.getPassword();
        char[] userPasswordAgain = registerPasswordFieldPasswordAgain.getPassword();

        OPAccount.Result r = OPAccount.register(userName, userPassword, userPasswordAgain);

        if (r.state) {
            // 重置密码评估信息
            registerLabelEvalution.setText("/");
        }
        JOptionPane.showMessageDialog(this, r.message);
    }

    private void registerPasswordFieldPasswordKeyReleased(KeyEvent e) {
        char[] userPassword = registerPasswordFieldPassword.getPassword();
        PasswordUtil.EvaluationResult er = PasswordUtil.evaluatePwd(userPassword);
        registerLabelEvalution.setText(er.toString());
    }

    private void forgotPasswordButtonConfirmMouseClicked(MouseEvent e) {
        if (bMailSended) {
            boolean bTimeout = false;
            boolean bOperationFinished = false;
            if (Calendar.getInstance().getTimeInMillis() - millsMailSendTime <= 5 * 60 * 1000) {
                String vc = forgotPasswordTextFieldVerificationCode.getText();
                if (vc.equals(verificationCode)) {
                    bOperationFinished = true;
                    ModifyAccountDialog mad = new ModifyAccountDialog(
                            this, accWantToChangePassword.getName(), accWantToChangePassword);
                    mad.setVisible(true);
                    mad.dispose();

                    Account accModified = (Account) mad.getInputData();
                    if (accModified != null) {
                        DBAccount.update(accWantToChangePassword.getName(), accModified);
                        JOptionPane.showMessageDialog(
                                this, "密码修改成功！", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    forgotPasswordLabelVerificationCodePrompt.setText("验证码错误");
                }
            } else {
                bTimeout = true;
            }

            if (bTimeout) {
                JOptionPane.showMessageDialog(
                        this, "验证码超时", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if (bTimeout || bOperationFinished){
                forgotPasswordButtonConfirm.setText("发送验证码");
                forgotPasswordLabelVerificationCodePrompt.setText("");
                bMailSended = false;
                forgotPasswordTextFieldAccountName.setEnabled(true);
            }
        } else {
            String accName = forgotPasswordTextFieldAccountName.getText();
            accWantToChangePassword = DBAccount.get(accName);
            // 判断账户是否存在
            if (accWantToChangePassword == null) {
                forgotPasswordLabelAccountNamePrompt.setText("账户不存在");
                return;
            } else {
                forgotPasswordLabelAccountNamePrompt.setText("");
            }

            // 判断该账户有没有绑定邮箱，也就是有没有绑定员工号
            if (accWantToChangePassword.getEmpNo() != null) {
                // 发送验证邮件
                verificationCode = VerificationCodeUtil.generate();
                try {
                    EmailUtil.sendMimeMessage(
                            accWantToChangePassword.getEmpNo(), accWantToChangePassword.getName(),
                            "人事管理系统 - 找回密码",
                            String.format("您的验证码是：%s，5分钟之内有效", verificationCode));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(
                            this, "邮件发送失败！", "提示",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                millsMailSendTime = Calendar.getInstance().getTimeInMillis();
                bMailSended = true;

                JOptionPane.showMessageDialog(
                        this, "验证码已发送", "提示",
                        JOptionPane.INFORMATION_MESSAGE);

                forgotPasswordButtonConfirm.setText("确认");
                forgotPasswordTextFieldAccountName.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(
                        this, "该账号没有绑定邮箱，请联系管理员修改密码！", "提示",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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
        panelForgotPassword = new JPanel();
        forgotPasswordLabel1 = new JLabel();
        forgotPasswordTextFieldAccountName = new JTextField();
        forgotPasswordLabelAccountNamePrompt = new JLabel();
        forgotPasswordLabel3 = new JLabel();
        forgotPasswordTextFieldVerificationCode = new JTextField();
        forgotPasswordLabelVerificationCodePrompt = new JLabel();
        forgotPasswordButtonConfirm = new JButton();
        forgotPasswordSeparator1 = new JSeparator();
        forgotPasswordLabel2 = new JLabel();

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
                panelLogin.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                        (0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                        .TitledBorder.BOTTOM, new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12), java.awt
                        .Color.red), panelLogin.getBorder()));
                panelLogin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    @Override
                    public void
                    propertyChange(java.beans.PropertyChangeEvent e) {
                        if ("\u0062or\u0064er".equals(e.getPropertyName())) throw new RuntimeException()
                                ;
                    }
                });
                panelLogin.setLayout(new GridBagLayout());
                ((GridBagLayout) panelLogin.getLayout()).columnWidths = new int[]{9, 0, 150};
                ((GridBagLayout) panelLogin.getLayout()).rowHeights = new int[]{31, 31, 34};

                //---- loginPasswordFieldPassword ----
                loginPasswordFieldPassword.setColumns(1);
                panelLogin.add(loginPasswordFieldPassword, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 0), 0, 0));

                //---- loginTextFieldUserName ----
                loginTextFieldUserName.setColumns(1);
                panelLogin.add(loginTextFieldUserName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 0), 0, 0));

                //---- loginLabelAccount ----
                loginLabelAccount.setText("\u8d26\u6237\uff1a");
                panelLogin.add(loginLabelAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- loginLabelPassword ----
                loginLabelPassword.setText("\u5bc6\u7801\uff1a");
                panelLogin.add(loginLabelPassword, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));

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
                ((GridBagLayout) panelRegister.getLayout()).columnWidths = new int[]{75, 0, 158, 0};
                ((GridBagLayout) panelRegister.getLayout()).rowHeights = new int[]{0, 0, 0, 7};

                //---- registerLabelAccount ----
                registerLabelAccount.setText("\u8d26\u6237\uff1a");
                registerLabelAccount.setHorizontalTextPosition(SwingConstants.CENTER);
                panelRegister.add(registerLabelAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerTextFieldAccount ----
                registerTextFieldAccount.setColumns(1);
                panelRegister.add(registerTextFieldAccount, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerLabelPassword ----
                registerLabelPassword.setText("\u5bc6\u7801\uff1a");
                panelRegister.add(registerLabelPassword, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerPasswordFieldPassword ----
                registerPasswordFieldPassword.setMaximumSize(null);
                registerPasswordFieldPassword.setMinimumSize(null);
                registerPasswordFieldPassword.setPreferredSize(null);
                registerPasswordFieldPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                registerPasswordFieldPassword.setColumns(1);
                registerPasswordFieldPassword.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        registerPasswordFieldPasswordKeyReleased(e);
                    }
                });
                panelRegister.add(registerPasswordFieldPassword, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerLabelEvalution ----
                registerLabelEvalution.setText("/");
                panelRegister.add(registerLabelEvalution, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 0), 0, 0));

                //---- registerLabelPasswordAgain ----
                registerLabelPasswordAgain.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
                registerLabelPasswordAgain.setMinimumSize(new Dimension(60, 17));
                panelRegister.add(registerLabelPasswordAgain, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerPasswordFieldPasswordAgain ----
                registerPasswordFieldPasswordAgain.setColumns(1);
                panelRegister.add(registerPasswordFieldPasswordAgain, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- registerButtonRegister ----
                registerButtonRegister.setText("\u6ce8\u518c");
                registerButtonRegister.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        registerButtonRegisterMouseReleased(e);
                    }
                });
                panelRegister.add(registerButtonRegister, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 8), 0, 0));
            }
            tabbedPane.addTab("\u6ce8\u518c", panelRegister);

            //======== panelForgotPassword ========
            {
                panelForgotPassword.setLayout(new GridBagLayout());
                ((GridBagLayout) panelForgotPassword.getLayout()).columnWidths = new int[]{0, 0, 158, 0};

                //---- forgotPasswordLabel1 ----
                forgotPasswordLabel1.setText("\u8d26\u6237\uff1a");
                panelForgotPassword.add(forgotPasswordLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));
                panelForgotPassword.add(forgotPasswordTextFieldAccountName, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 8), 0, 0));
                panelForgotPassword.add(forgotPasswordLabelAccountNamePrompt, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 0), 0, 0));

                //---- forgotPasswordLabel3 ----
                forgotPasswordLabel3.setText("\u9a8c\u8bc1\u7801\uff1a");
                panelForgotPassword.add(forgotPasswordLabel3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));
                panelForgotPassword.add(forgotPasswordTextFieldVerificationCode, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 8), 0, 0));
                panelForgotPassword.add(forgotPasswordLabelVerificationCodePrompt, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 8, 0), 0, 0));

                //---- forgotPasswordButtonConfirm ----
                forgotPasswordButtonConfirm.setText("\u53d1\u9001\u9a8c\u8bc1\u7801");
                forgotPasswordButtonConfirm.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        forgotPasswordButtonConfirmMouseClicked(e);
                    }
                });
                panelForgotPassword.add(forgotPasswordButtonConfirm, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 8, 8), 0, 0));
                panelForgotPassword.add(forgotPasswordSeparator1, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 8, 8), 0, 0));

                //---- forgotPasswordLabel2 ----
                forgotPasswordLabel2.setText("\u901a\u8fc7\u7ed1\u5b9a\u7684\u90ae\u7bb1\u627e\u56de\u5bc6\u7801");
                panelForgotPassword.add(forgotPasswordLabel2, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 8), 0, 0));
            }
            tabbedPane.addTab("\u5fd8\u8bb0\u5bc6\u7801", panelForgotPassword);
        }
        contentPane.add(tabbedPane);
        setSize(365, 255);
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
    private JPanel panelForgotPassword;
    private JLabel forgotPasswordLabel1;
    private JTextField forgotPasswordTextFieldAccountName;
    private JLabel forgotPasswordLabelAccountNamePrompt;
    private JLabel forgotPasswordLabel3;
    private JTextField forgotPasswordTextFieldVerificationCode;
    private JLabel forgotPasswordLabelVerificationCodePrompt;
    private JButton forgotPasswordButtonConfirm;
    private JSeparator forgotPasswordSeparator1;
    private JLabel forgotPasswordLabel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    //测试LoginFrame类
    public static void main(String[] args) {
        JFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}
