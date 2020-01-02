/*
 * Created by JFormDesigner on Thu Jan 02 19:22:03 CST 2020
 */

package PMS.gui.employee;

import java.awt.event.*;
import PMS.db.DBDepartment;
import PMS.db.DBEmployee;
import PMS.entity.Department;
import PMS.entity.Employee;

import java.awt.*;
import java.sql.Date;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author c
 */
public class AddingEmployeeDialog extends JDialog {
    public AddingEmployeeDialog(Window owner) {
        super(owner);
        initComponents();

        // 初始化
        Iterator<Department> iterator = DBDepartment.getAll();
        while (iterator.hasNext()) {
            Department d = iterator.next();
            comboBoxEmpDeptNo.addItem(d);
        }
    }

    private Employee getData() {
        Employee e = new Employee();
        e.setNo(textFieldEmpNo.getText());
        e.setName(textFieldEmpName.getText());
        e.setBirthday(Date.valueOf(textFieldEmpBirthday.getText()));
        e.setDepNo((String) comboBoxEmpDeptNo.getSelectedItem());
        e.setTitle((String) comboBoxEmpTitle.getSelectedItem());
        e.setClockingIn(0);

        return e;
    }

    private void okButtonMouseReleased(MouseEvent e) {
        DBEmployee.add(getData());

        this.setVisible(false);
    }


    private void cancelButtonMouseReleased(MouseEvent e) {
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label7 = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        textFieldEmpClockingIn = new JTextField();
        comboBoxEmpTitle = new JComboBox();
        comboBoxEmpDeptNo = new JComboBox<>();
        textFieldEmpBirthday = new JTextField();
        textFieldEmpName = new JTextField();
        textFieldEmpNo = new JTextField();
        label6 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {1.0, 1.0};

                //---- label7 ----
                label7.setText("\u8f93\u5165\u5458\u5de5\u8be6\u7ec6\u4fe1\u606f\u3002");
                contentPanel.add(label7, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label1 ----
                label1.setText("empNo\uff1a");
                contentPanel.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("empName\uff1a");
                contentPanel.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("empBirthday\uff1a");
                contentPanel.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label4 ----
                label4.setText("empDeptNo\uff1a");
                contentPanel.add(label4, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label5 ----
                label5.setText("empTitle\uff1a");
                contentPanel.add(label5, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textFieldEmpClockingIn ----
                textFieldEmpClockingIn.setText("0");
                textFieldEmpClockingIn.setEditable(false);
                contentPanel.add(textFieldEmpClockingIn, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                contentPanel.add(comboBoxEmpTitle, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(comboBoxEmpDeptNo, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpBirthday, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textFieldEmpNo, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label6 ----
                label6.setText("empClockingIn\uff1a");
                contentPanel.add(label6, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
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
                cancelButton.setText("Cancel");
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
        setSize(535, 375);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label7;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textFieldEmpClockingIn;
    private JComboBox comboBoxEmpTitle;
    private JComboBox<Department> comboBoxEmpDeptNo;
    private JTextField textFieldEmpBirthday;
    private JTextField textFieldEmpName;
    private JTextField textFieldEmpNo;
    private JLabel label6;
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