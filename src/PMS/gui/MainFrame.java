/*
 * Created by JFormDesigner on Wed Jan 01 20:21:55 CST 2020
 */

package PMS.gui;

import PMS.entity.Account;
import PMS.gui.employee.EmployeeManagementPanel;

import PMS.db.DBAccount;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author c
 */
public class MainFrame extends JFrame {
    // 登录该窗口的账号
    private Account loginAccount;
    // 子面板
    private JPanel panelEmployeeManagement;


    public MainFrame(Account loginAccount) {

        initComponents();

        // 自定义初始化

        // 设置该窗口对应的账号
        this.loginAccount = loginAccount;
        // 添加面板
        panelEmployeeManagement = new EmployeeManagementPanel();
        tabbedPane.addTab("员工管理", panelEmployeeManagement);
    }


    private void thisWindowClosing(WindowEvent e) {
        // 弹出对话框确认关闭行为
        int optionValue = JOptionPane.showConfirmDialog(
                this, "是否确认关闭？","提示",
                JOptionPane.OK_CANCEL_OPTION);

        // 如果点击确定则结束程序，否则什么都不做
        if (optionValue == JOptionPane.OK_OPTION) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        menuBar = new JMenuBar();
        menu1 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem4 = new JMenuItem();
        tabbedPane = new JTabbedPane();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
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

            //======== menu1 ========
            {
                menu1.setText("\u8d26\u53f7");

                //---- menuItem3 ----
                menuItem3.setText("\u6253\u5361");
                menu1.add(menuItem3);

                //---- menuItem1 ----
                menuItem1.setText("\u4fee\u6539\u5bc6\u7801");
                menu1.add(menuItem1);
            }
            menuBar.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u5e2e\u52a9");

                //---- menuItem2 ----
                menuItem2.setText("\u5e2e\u52a9\u5185\u5bb9");
                menu2.add(menuItem2);

                //---- menuItem4 ----
                menuItem4.setText("\u5173\u4e8e");
                menu2.add(menuItem4);
            }
            menuBar.add(menu2);
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
    private JMenu menu1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JMenuItem menuItem4;
    private JTabbedPane tabbedPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // 测试
    public static void main(String[] args) {
        // 用admin账号进行测试
        Account accAdmin = DBAccount.get("admin");
        MainFrame mainFrame = new MainFrame(accAdmin);
        mainFrame.setVisible(true);
    }
}
