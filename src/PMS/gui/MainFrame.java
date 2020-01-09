/*
 * Created by JFormDesigner on Wed Jan 01 20:21:55 CST 2020
 */

package PMS.gui;

import PMS.client.Client;
import PMS.db.DBClockingIn;
import PMS.db.DBClockinginTime;
import PMS.db.util.MySqlUtil;
import PMS.entity.Account;
import PMS.entity.ClockingIn;
import PMS.entity.Employee;
import PMS.db.DBAccount;
import PMS.db.DBEmployee;
import PMS.gui.department.DepartmentManagementPanel;
import PMS.gui.employee.EmployeeManagementPanel;
import PMS.gui.employee.EmployeeInterfacePanel;
import PMS.gui.account.ModifyAccountDialog;
import PMS.gui.message.MessagePanel;
import PMS.gui.help.HelpDialog;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.*;



/**
 * @author c
 */
public class MainFrame extends JFrame {
    // 登录该窗口的账号
    private Account accCurr;
    // 子面板
    private EmployeeManagementPanel panelEmployeeManagement;
    private DepartmentManagementPanel DepartmentManagementPanel;
    private EmployeeInterfacePanel employeeInterfacePanel;
    private MessagePanel messagePanel;
    // 客户端
    private Client clientCurr;


    public MainFrame(Account accountCurrent, Client clientCurrent) {
        initComponents();

        // 设置该窗口对应的账号
        accCurr = accountCurrent;
        clientCurr = clientCurrent;

        // 设置强制下线handler
        clientCurr.setClientForcedOfflineHandler(() -> {
            JOptionPane.showMessageDialog(this,
                    "账号从另一地登录，程序即将退出", "提示", JOptionPane.ERROR_MESSAGE);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭时退出程序
            this.dispose();
        });

        // 设置标题
        initTitle();

        // 添加专有面板
        if (this.accCurr.isPersonnelStaff()) {
            panelEmployeeManagement = new EmployeeManagementPanel(accCurr);
            DepartmentManagementPanel = new DepartmentManagementPanel();
            tabbedPane.addTab("员工管理", panelEmployeeManagement);
            tabbedPane.addTab("部门管理", DepartmentManagementPanel);
            menuItemClockingIn.setVisible(false);
        } else {
            employeeInterfacePanel = new EmployeeInterfacePanel(accCurr);
            tabbedPane.addTab("个人信息查询", employeeInterfacePanel);
        }
        // 添加通用面板
        messagePanel = new MessagePanel(accCurr, clientCurr);
        tabbedPane.add("消息反馈", messagePanel);
    }

    // 初始化窗口标题
    private void initTitle() {
        String bindInfo;
        Employee loginEmployee = DBEmployee.get(accCurr.getEmpNo());
        if (loginEmployee != null) {
            bindInfo = loginEmployee.getName();
        } else {
            bindInfo = "未绑定员工";
        }
        this.setTitle(String.format("[当前账号：%s - %s] - 人事管理系统",
                accCurr.getName(), bindInfo));
    }

    private void thisWindowClosing(WindowEvent e) {
        // 弹出对话框确认关闭行为
        int optionValue = JOptionPane.showConfirmDialog(
                this, "是否确认关闭？", "提示",
                JOptionPane.OK_CANCEL_OPTION);

        // 如果点击确定则结束程序，否则什么都不做
        if (optionValue == JOptionPane.OK_OPTION) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭时退出程序
            clientCurr.disconnectAndStop(); // 关闭客户端
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private void menuItemModifyPasswordMouseReleased(MouseEvent e) {
        ModifyAccountDialog modifyAccount =
                new ModifyAccountDialog(null, accCurr.getName(), accCurr);
        modifyAccount.setVisible(true);
        modifyAccount.dispose();

        Account account = (Account) modifyAccount.getInputData();
        if (account != null) {
            System.out.println(account); // 调试
            DBAccount.update(account.getName(), account);
        }
    }

    //打卡
    private void menuItem3MouseReleased(MouseEvent e) {
        ClockingIn clockingIn = new ClockingIn();
        clockingIn.setCleno(accCurr.getEmpNo());
        clockingIn.setCldatetime(Timestamp.valueOf(LocalDateTime.now()));
//        System.out.println(EmployeeManagementPanel.getClockingInTime());
        if (Time.valueOf(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond()).after(DBClockinginTime.get())) {
            System.out.println(Timestamp.valueOf(LocalDateTime.now()) + "    " + DBClockinginTime.get());
            clockingIn.setClstatus(ClockingIn.late);
        } else if (Time.valueOf(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond()).before(DBClockinginTime.get())) {
            clockingIn.setClstatus(ClockingIn.ontime);
        }

        String SQL = "SELECT * FROM  clockingin WHERE DATE(Cldatetime)=? AND Cleno=?";
        try {
            PreparedStatement temp = MySqlUtil.prepareStatement(SQL);
            temp.setString(1, clockingIn.getCldatetime().toString().substring(0, 10));
            temp.setString(2, accCurr.getEmpNo());
            ResultSet resultSet = temp.executeQuery();


            if (!resultSet.next()) {
                DBClockingIn.add(clockingIn);
//            DBAccessUtil.updateWrapped("UPDATE EMPLOYEE SET empClockingIn=empClockingIn +1 WHERE empNo=?", clockingIn.getCleno());
                JOptionPane.showMessageDialog(this,
                        "你好" + accCurr.getName() + "，考勤状态：" + clockingIn.getClstatus(), "打卡成功", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "本日已打卡，请勿重复打卡", "打卡失败", JOptionPane.ERROR_MESSAGE);
            }
            employeeInterfacePanel.refresh(accCurr.getEmpNo());
        } catch (SQLException ignored) {
        }
    }


    private void menuItem1MouseReleased(MouseEvent e) {
        this.setVisible(false);
        this.dispose();

        clientCurr.disconnectAndStop();

        Frame f = new LoginFrame();
        f.setVisible(true);

    }

    private void menuItemHelpMouseClicked(MouseEvent e) {
    }

    private void menuItemHelpMouseReleased(MouseEvent e) {
        HelpDialog hd = new HelpDialog(this);
        hd.setVisible(true);
        hd.dispose();
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        menuBar = new JMenuBar();
        menuAccount = new JMenu();
        menuItemClockingIn = new JMenuItem();
        menuItemModifyPassword = new JMenuItem();
        menuItemExit = new JMenuItem();
        menuHelp = new JMenu();
        menuItemHelp = new JMenuItem();
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
                        menuItem3MouseReleased(e);
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

                //---- menuItemExit ----
                menuItemExit.setText("\u9000\u51fa\u767b\u5f55");
                menuItemExit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItem1MouseReleased(e);
                    }
                });
                menuAccount.add(menuItemExit);
            }
            menuBar.add(menuAccount);

            //======== menuHelp ========
            {
                menuHelp.setText("\u5e2e\u52a9");

                //---- menuItemHelp ----
                menuItemHelp.setText("\u5e2e\u52a9\u5185\u5bb9");
                menuItemHelp.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        menuItemHelpMouseClicked(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItemHelpMouseReleased(e);
                    }
                });
                menuHelp.add(menuItemHelp);
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
    private JMenuItem menuItemExit;
    private JMenu menuHelp;
    private JMenuItem menuItemHelp;
    private JTabbedPane tabbedPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // 测试
    public static void main(String[] args) {
        Account acc = DBAccount.get("personnelStaff");
        Client cli = new Client(acc);
        cli.startAndConnect();

        MainFrame mainFrame = new MainFrame(acc, cli);
        mainFrame.setVisible(true);
    }
}
