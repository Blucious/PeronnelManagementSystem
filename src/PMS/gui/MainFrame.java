/*
 * Created by JFormDesigner on Wed Jan 01 20:21:55 CST 2020
 */

package PMS.gui;

import PMS.entity.Account;
import PMS.entity.Employee;
import PMS.db.DBAccount;
import PMS.db.DBEmployee;
import PMS.gui.employee.EmployeeManagementPanel;
import PMS.gui.employee.EmployeeInterfacePanel;
import PMS.gui.message.MessagePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.gui.account.ModifyAccountDialog;


/**
 * @author c
 */
public class MainFrame extends JFrame {
    // 登录该窗口的账号
    private Account loginAccount;
    // 子面板
    private EmployeeManagementPanel panelEmployeeManagement;
    private EmployeeInterfacePanel employeeInterfacePanel;
    private MessagePanel messagePanel;

    public MainFrame(Account loginAccount) {
        initComponents();

        // 自定义初始化

        // 设置该窗口对应的账号
        this.loginAccount = loginAccount;

        // 设置标题
        String bindInfo;
        Employee loginEmployee = DBEmployee.get(loginAccount.getEmpNo());
        if (loginEmployee != null) {
            bindInfo = loginEmployee.getName();
        } else {
            bindInfo = "未绑定员工";
        }
        this.setTitle(String.format("[当前账号：%s - %s] - 人事管理系统",
                loginAccount.getName(), bindInfo));

        // 添加面板
        // 专有面板
        if (this.loginAccount.isPersonnelStaff()) {
            panelEmployeeManagement = new EmployeeManagementPanel();
            tabbedPane.addTab("员工管理", panelEmployeeManagement);
        } else {
            employeeInterfacePanel = new EmployeeInterfacePanel(loginAccount.getEmpNo());
            tabbedPane.addTab("个人信息查询", employeeInterfacePanel);
        }
        // 通用面板
        messagePanel = new MessagePanel();
        tabbedPane.add("消息反馈", messagePanel);

    }


    private void thisWindowClosing(WindowEvent e) {
        // 弹出对话框确认关闭行为
        int optionValue = JOptionPane.showConfirmDialog(
                this, "是否确认关闭？", "提示",
                JOptionPane.OK_CANCEL_OPTION);

        // 如果点击确定则结束程序，否则什么都不做
        if (optionValue == JOptionPane.OK_OPTION) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private void menuItemClockingInMouseReleased(MouseEvent e) {
        // TODO add your code here
    }

    private void menuItemModifyPasswordMouseReleased(MouseEvent e) {
        ModifyAccountDialog modifyAccount =
                new ModifyAccountDialog(null, loginAccount.getName(),loginAccount);
        modifyAccount.setVisible(true);
        modifyAccount.dispose();

        Account account = (Account) modifyAccount.getInputData();
        if (account != null) {
            System.out.println(account); // 调试
            DBAccount.update(account.getName(), account);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        menuBar = new JMenuBar();
        menuAccount = new JMenu();
        menuItemClockingIn = new JMenuItem();
        menuItemModifyPassword = new JMenuItem();
        menuHelp = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem4 = new JMenuItem();
        tabbedPane = new JTabbedPane();

        //======== this ========
        setMinimumSize(new Dimension(800, 540));
        setName("frame0");
        setTitle("\u4eba\u4e8b\u7ba1\u7406\u7cfb\u7edf");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== menuBar ========
        {

            //======== menuAccount ========
            {
                menuAccount.setText("\u8d26\u53f7");

                //---- menuItemClockingIn ----
                menuItemClockingIn.setText("\u6253\u5361");
                menuItemClockingIn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemClockingInMouseReleased(e);
                    }
                });
                menuAccount.add(menuItemClockingIn);

                //---- menuItemModifyPassword ----
                menuItemModifyPassword.setText("\u4fee\u6539\u5bc6\u7801");
                menuItemModifyPassword.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemModifyPasswordMouseReleased(e);
                    }
                });
                menuAccount.add(menuItemModifyPassword);
            }
            menuBar.add(menuAccount);

            //======== menuHelp ========
            {
                menuHelp.setText("\u5e2e\u52a9");

                //---- menuItem2 ----
                menuItem2.setText("\u5e2e\u52a9\u5185\u5bb9");
                menuHelp.add(menuItem2);

                //---- menuItem4 ----
                menuItem4.setText("\u5173\u4e8e");
                menuHelp.add(menuItem4);
            }
            menuBar.add(menuHelp);
        }
        setJMenuBar(menuBar);

        //======== tabbedPane ========
        {
            tabbedPane.setPreferredSize(new Dimension(200, 200));
            tabbedPane.setMinimumSize(null);
        }
        contentPane.add(tabbedPane);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JMenuBar menuBar;
    private JMenu menuAccount;
    private JMenuItem menuItemClockingIn;
    private JMenuItem menuItemModifyPassword;
    private JMenu menuHelp;
    private JMenuItem menuItem2;
    private JMenuItem menuItem4;
    private JTabbedPane tabbedPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // 测试
    public static void main(String[] args) {
        Account acc = DBAccount.get("personnelStaff");
        MainFrame mainFrame = new MainFrame(acc);
        mainFrame.setVisible(true);
    }
}
