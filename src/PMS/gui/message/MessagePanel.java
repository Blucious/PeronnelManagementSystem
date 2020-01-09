/*
 * Created by JFormDesigner on Sun Jan 05 03:20:22 CST 2020
 */

package PMS.gui.message;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import PMS.client.Client;
import PMS.db.DBMessage;
import PMS.db.DBAccount;
import PMS.entity.Message;
import PMS.entity.Account;
import PMS.gui.model.TableModelConnectionless;

import javax.swing.*;

/**
 * @author c
 */
public class MessagePanel extends JPanel {
    // 当前账户
    private Account accCurr;
    // 当前客户端
    private Client clientCurr;
    // 表数据模型
    private TableModelConnectionless tableModelReceivedMessage;

    SimpleDateFormat simpleDateFormat;

    public MessagePanel(Account accountCurrent, Client clientCurrent) {
        initComponents();

        accCurr = accountCurrent; // 设置当前账号
        clientCurr = clientCurrent; // 设置当前客户端
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间显示格式

        // 设置更新消息handler
        clientCurr.setClientUpdateHandler(this::refreshTableModel);

        tableModelReceivedMessage = new TableModelConnectionless(); // 创建表数据模型
        tableReceivedMessage.setModel(tableModelReceivedMessage); // 设置表数据模型

        refreshTableModel(); // 刷新视图
    }

    private void refreshTableModel() {
        String sql = "select msgNo, accName, empName as 员工名, msgSendTime as 发送时间, msgMessage as 消息内容 " +
                "from message join account on (msgSenderAccName=accName) join employee on (accEmpNo=empNo) " +
                "where msgReceiverAccName=?";
        tableModelReceivedMessage.setDisplayOffset(2);
        tableModelReceivedMessage.setQuery(sql, accCurr.getName());
    }

    // 事件：查看消息
    private void tableReceivedMessageMouseReleased(MouseEvent e) {
        // 左键双击查看消息
        if (e.getButton() == MouseEvent.BUTTON1
                && e.getClickCount() == 2) {

            int selectedRow = tableReceivedMessage.getSelectedRow();

            String empName = (String) tableReceivedMessage.getValueAt(selectedRow, 0);
            Date sendTime = (Date) tableReceivedMessage.getValueAt(selectedRow, 1);
            String message = (String) tableReceivedMessage.getValueAt(selectedRow, 2);
            String sendTimeStr = simpleDateFormat.format(sendTime);

            String text = String.format("发送人：%s\n" +
                    "发送时间：%s\n" +
                    "——————————————————————————\n" +
                    "%s", empName, sendTimeStr, message);

            // 显示消息详细信息
            MessageDisplayDialog mdd = new MessageDisplayDialog(null, text);
            mdd.setVisible(true);
            mdd.dispose();

            MessageDisplayDialog.Result r = (MessageDisplayDialog.Result) mdd.getInputData();
            if (r != null && r.doReplay) { // 是要否进行回复
                String accName = (String) tableModelReceivedMessage.getOriginValueAt(
                        selectedRow, 1);
                Account accReceiver = DBAccount.get(accName);

                SendMessageDialog smd = new SendMessageDialog(accCurr, accReceiver);
                smd.setVisible(true);
                smd.dispose();

                SendMessageDialog.Result smdr = (SendMessageDialog.Result) smd.getInputData();
                if (smdr != null) {
                    sendMessage(accCurr, smdr.accReceiverList, smdr.message);
                }
            }
        } // 左键双击

    }

    // 事件：刷新消息
    private void buttonRefreshMouseReleased(MouseEvent e) {
        refreshTableModel();
    }

    // 发送消息
    private void sendMessage(Account accSender, List<Account> accReceiverList, String message) {
        for (Account accReceiver : accReceiverList) {
            Message m = new Message(
                    accSender.getName(), accReceiver.getName(), message);
            DBMessage.add(m);
            clientCurr.sendMsgNotifyOtherReceiveMessage(accReceiver);
        }
    }


    // 事件：发送消息
    private void buttonSendMessageMouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // 左键
            SendMessageDialog d = new SendMessageDialog(accCurr);
            d.setVisible(true);
            d.dispose();

            SendMessageDialog.Result r = (SendMessageDialog.Result) d.getInputData();
            if (r != null) {
                sendMessage(accCurr, r.accReceiverList, r.message);
            }
        }
    }

    // 事件：删除消息
    private void buttonDeleteMouseClicked(MouseEvent e) {
        int[] selectedColumns = tableReceivedMessage.getSelectedRows();
        // 如果没有选中任何一行，则不继续处理，也不做响应
        if (selectedColumns.length == 0) {
            return;
        }

        // 让用户确认删除
        int opt = JOptionPane.showConfirmDialog(this,
                String.format("是否删除所选中的%d条消息？删除后不可恢复！", selectedColumns.length),
                "提示",
                JOptionPane.OK_CANCEL_OPTION);

        if (opt == JOptionPane.OK_OPTION) { // 用户点击确认
            for (int selectedColumn : selectedColumns) {
                int msgNo = (int) tableModelReceivedMessage.getOriginValueAt(
                        selectedColumn, 0);
                DBMessage.delete(msgNo);
            }
            refreshTableModel();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        buttonSendMessage = new JButton();
        separator1 = new JSeparator();
        panel1 = new JPanel();
        buttonRefresh = new JButton();
        buttonDelete = new JButton();
        scrollPane1 = new JScrollPane();
        tableReceivedMessage = new JTable();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
        .EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER,javax
        .swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font.BOLD,
        12),java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans
        .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er".equals(e.
        getPropertyName()))throw new RuntimeException();}});
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 60, 0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0E-4};

        //---- buttonSendMessage ----
        buttonSendMessage.setText("\u53d1\u9001\u6d88\u606f");
        buttonSendMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                buttonSendMessageMouseReleased(e);
            }
        });
        add(buttonSendMessage, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(0, 0, 5, 5), 0, 0));
        add(separator1, new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            ((FlowLayout)panel1.getLayout()).setAlignOnBaseline(true);

            //---- buttonRefresh ----
            buttonRefresh.setText("\u5237\u65b0");
            buttonRefresh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    buttonRefreshMouseReleased(e);
                }
            });
            panel1.add(buttonRefresh);

            //---- buttonDelete ----
            buttonDelete.setText("\u5220\u9664");
            buttonDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDeleteMouseClicked(e);
                }
            });
            panel1.add(buttonDelete);
        }
        add(panel1, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== scrollPane1 ========
        {

            //---- tableReceivedMessage ----
            tableReceivedMessage.setAutoCreateRowSorter(true);
            tableReceivedMessage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    tableReceivedMessageMouseReleased(e);
                }
            });
            scrollPane1.setViewportView(tableReceivedMessage);
        }
        add(scrollPane1, new GridBagConstraints(0, 3, 4, 3, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JButton buttonSendMessage;
    private JSeparator separator1;
    private JPanel panel1;
    private JButton buttonRefresh;
    private JButton buttonDelete;
    private JScrollPane scrollPane1;
    private JTable tableReceivedMessage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
