/*
 * Created by JFormDesigner on Thu Jan 02 19:22:03 CST 2020
 */

package PMS.gui.employee;

import PMS.db.DBDepartment;
import PMS.db.DBTitle;
import PMS.entity.Department;
import PMS.entity.Employee;
import PMS.entity.Title;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.ComboBoxModelDepartment;
import PMS.gui.model.ComboBoxModelTitle;
import PMS.op.OPEmployee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**
 * @author c
 */
public class ModifyEmployeeDialog extends DataInputDialog {

    private Employee empOriginal;
    private ComboBoxModelDepartment comboBoxModelDepartment;
    private ComboBoxModelTitle comboBoxModelTitle;

    public ModifyEmployeeDialog(Employee emp) {
        initComponents();

        empOriginal = emp;
        // 控件值初始化

        textFieldEmpNo.setText(emp.getNo()); // 设置名字原始值
        textFieldEmpName.setText(emp.getName()); // 设置名字原始值
        textFieldEmpBirthday.setText(emp.getBirthdayString()); // 设置名字原始值

        // 设置部门可选值
        Iterator<Department> itDept = DBDepartment.getAll();
        comboBoxModelDepartment = new ComboBoxModelDepartment(itDept);
        comboBoxEmpDeptNo.setModel(comboBoxModelDepartment);
        comboBoxModelDepartment.setSelectedItemByNo(emp.getDepNo()); // 设置部门原始值

        // 设置头衔可选值
        Iterator<Title> itTitle = DBTitle.getAll();
        comboBoxModelTitle = new ComboBoxModelTitle(itTitle);
        comboBoxEmpTitle.setModel(comboBoxModelTitle);
        comboBoxModelTitle.setSelectedItemByName(emp.getTitle()); // 设置头衔原始值
    }

    private void okButtonMouseClicked(MouseEvent e) {
        String no = textFieldEmpNo.getText();
        String name = textFieldEmpName.getText();
        String birthdayString = textFieldEmpBirthday.getText();
        String depNo = comboBoxModelDepartment.getOriginalSelectedItem().getNo();
        String title = comboBoxModelTitle.getOriginalSelectedItem().getName();

        Employee newEmp = OPEmployee.modifyIsValid(empOriginal,
                no, name, birthdayString, depNo, title);
        if (newEmp == null) {
            return;
        }

        // 设置要返回的数据
        setInputData(newEmp);

        // 设置窗口为不可见，从而使调用者停止阻塞
        this.setVisible(false);
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        comboBoxEmpTitle = new JComboBox<>();
        comboBoxEmpDeptNo = new JComboBox<>();
        textFieldEmpBirthday = new JTextField();
        textFieldEmpName = new JTextField();
        textFieldEmpNo = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("\u4fee\u6539\u5458\u5de5\u4fe1\u606f");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 250};

                //---- label1 ----
                label1.setText("\u5458\u5de5\u53f7\uff1a");
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("\u5458\u5de5\u59d3\u540d\uff1a");
                contentPanel.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u5458\u5de5\u751f\u65e5\uff1a");
                contentPanel.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label4 ----
                label4.setText("\u5458\u5de5\u6240\u5c5e\u90e8\u95e8\uff1a");
                contentPanel.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label5 ----
                label5.setText("\u5458\u5de5\u5934\u8854\uff1a");
                contentPanel.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 5), 0, 0));
                contentPanel.add(comboBoxEmpTitle, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                contentPanel.add(comboBoxEmpDeptNo, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpBirthday, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpNo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u4fee\u6539");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(455, 320);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JComboBox<String> comboBoxEmpTitle;
    private JComboBox<String> comboBoxEmpDeptNo;
    private JTextField textFieldEmpBirthday;
    private JTextField textFieldEmpName;
    private JTextField textFieldEmpNo;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    // test
    public static void main(String[] args) {
        JDialog d = new ModifyEmployeeDialog(null);
        d.setVisible(true);
    }
}
