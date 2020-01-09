/*
 * Created by JFormDesigner on Thu Jan 02 19:22:03 CST 2020
 */

package PMS.gui.employee;

import java.awt.event.*;

import PMS.db.DBDepartment;
import PMS.db.DBTitle;
import PMS.entity.Department;
import PMS.entity.Employee;
import PMS.entity.Title;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.ComboBoxModelDepartment;
import PMS.gui.model.ComboBoxModelTitle;
import PMS.gui.listener.TextFieldHintListener;
import PMS.op.OPEmployee;

import java.awt.*;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author c
 */
public class AddingEmployeeDialog extends DataInputDialog {

    private ComboBoxModelDepartment comboBoxModelDepartment;
    private ComboBoxModelTitle comboBoxModelTitle;

    public AddingEmployeeDialog(Window owner) {
        super(owner);
        initComponents();

        // 设置部门可选值
        Iterator<Department> itDept = DBDepartment.getAll();
        comboBoxModelDepartment = new ComboBoxModelDepartment(itDept);
        comboBoxEmpDepartment.setModel(comboBoxModelDepartment);

        // 设置头衔可选值
        Iterator<Title> itTitle = DBTitle.getAll();
        comboBoxModelTitle = new ComboBoxModelTitle(itTitle);
        comboBoxEmpTitle.setModel(comboBoxModelTitle);

        // 设置文本框提示内容
        textFieldEmpBirthday.addFocusListener(
                new TextFieldHintListener(textFieldEmpBirthday, "yyyy-m[m]-d[d]"));
    }

    private void okButtonMouseReleased(MouseEvent e) {
        String no = textFieldEmpNo.getText();
        String name = textFieldEmpName.getText();
        String birthdayString = textFieldEmpBirthday.getText();
        Department depSelected = comboBoxModelDepartment.getOriginalSelectedItem();
        if (depSelected == null) {
            JOptionPane.showMessageDialog(null, "未设置员工所属部门",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String depNo = depSelected.getNo();
        Title titSeleted = comboBoxModelTitle.getOriginalSelectedItem();
        if (titSeleted == null) {
            JOptionPane.showMessageDialog(null, "未选择员头衔",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String title = titSeleted.getName();

        Employee newEmp = OPEmployee.modifyIsValid(null,
                no, name, birthdayString, depNo, title);

        // 设置要返回的数据
        setInputData(newEmp);

        // 设置窗口为不可见，从而使调用者停止阻塞
        this.setVisible(false);
    }


    private void cancelButtonMouseReleased(MouseEvent e) {
        // 设置窗口为不可见，从而使调用者停止阻塞
        this.setVisible(false);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label7 = new JLabel();
        label1 = new JLabel();
        comboBoxEmpTitle = new JComboBox<>();
        comboBoxEmpDepartment = new JComboBox<>();
        textFieldEmpBirthday = new JTextField();
        textFieldEmpName = new JTextField();
        textFieldEmpNo = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("\u6dfb\u52a0\u5458\u5de5");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                    .EmptyBorder(0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax.swing.border.TitledBorder.CENTER, javax
                    .swing.border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD,
                    12), java.awt.Color.red), dialogPane.getBorder()));
            dialogPane.addPropertyChangeListener(new java.beans
                    .PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("\u0062or\u0064er".equals(e.
                            getPropertyName())) throw new RuntimeException();
                }
            });
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[]{0, 250};
                ((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[]{0, 13, 0, 0, 0, 0, 0};

                //---- label7 ----
                label7.setText("\u8bf7\u8f93\u5165\u5458\u5de5\u8be6\u7ec6\u4fe1\u606f");
                contentPanel.add(label7, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                //---- label1 ----
                label1.setText("\u5458\u5de5\u53f7\uff1a");
                contentPanel.add(label1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(comboBoxEmpTitle, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                contentPanel.add(comboBoxEmpDepartment, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpBirthday, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpName, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpNo, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setText("\u5458\u5de5\u59d3\u540d\uff1a");
                contentPanel.add(label2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u5458\u5de5\u751f\u65e5\uff1a");
                contentPanel.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label4 ----
                label4.setText("\u5458\u5de5\u6240\u5c5e\u90e8\u95e8\uff1a");
                contentPanel.add(label4, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- label5 ----
                label5.setText("\u5458\u5de5\u5934\u8854\uff1a");
                contentPanel.add(label5, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 0, 5), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 85, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u6dfb\u52a0");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        cancelButtonMouseReleased(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(470, 330);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label7;
    private JLabel label1;
    private JComboBox<String> comboBoxEmpTitle;
    private JComboBox<String> comboBoxEmpDepartment;
    private JTextField textFieldEmpBirthday;
    private JTextField textFieldEmpName;
    private JTextField textFieldEmpNo;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // test
    public static void main(String[] args) {
        JDialog d = new AddingEmployeeDialog(null);
        d.setVisible(true);
    }
}
