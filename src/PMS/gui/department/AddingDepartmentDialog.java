/*
 * Created by JFormDesigner on Wed Jan 08 17:34:10 CST 2020
 */

package PMS.gui.department;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.entity.Department;
import PMS.gui.com.DataInputDialog;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class AddingDepartmentDialog extends DataInputDialog {
    public AddingDepartmentDialog(Window owner) {
        super(owner);
        initComponents();
    }

    private void okButtonMouseClicked(MouseEvent e) {
        Department department = new Department();
        department.setName(textFieldDeptName.getText());
        department.setNo(textFieldDeptNo.getText());
        setInputData(department);
        setVisible(false);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - she
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label7 = new JLabel();
        textFieldDeptName = new JTextField();
        textFieldDeptNo = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
            0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
            . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
            red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
            beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)contentPanel2.getLayout()).columnWidths = new int[] {0, 280};
                    ((GridBagLayout)contentPanel2.getLayout()).rowHeights = new int[] {38, 40, 40, 0, 0, 0, 0};
                    ((GridBagLayout)contentPanel2.getLayout()).columnWeights = new double[] {1.0, 1.0};

                    //---- label1 ----
                    label1.setText("DeptNo\uff1a");
                    contentPanel2.add(label1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label2 ----
                    label2.setText("DeptName\uff1a");
                    contentPanel2.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label7 ----
                    label7.setText("\u8f93\u5165\u90e8\u95e8\u8be6\u7ec6\u4fe1\u606f\u3002");
                    contentPanel2.add(label7, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                    contentPanel2.add(textFieldDeptName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                    contentPanel2.add(textFieldDeptNo, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                contentPanel.add(contentPanel2, "cell 0 0");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - she
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label7;
    private JTextField textFieldDeptName;
    private JTextField textFieldDeptNo;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
