/*
 * Created by JFormDesigner on Mon Jan 06 22:04:23 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import javax.swing.*;

import PMS.entity.ClockingIn;
import PMS.gui.com.DataInputDialog;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class ModifyClockingin extends DataInputDialog {
    public ModifyClockingin(Window owner,String datetime,String eno) {
        super(owner);
        initComponents();
        textFieldeno.setText(eno);
        textFielddatetime.setText(datetime);
        ComboxClockingin.addItem(ClockingIn.ontime);
        ComboxClockingin.addItem(ClockingIn.late);
        ComboxClockingin.addItem(ClockingIn.absenteeism);

    }

    private void okButtonMouseReleased(MouseEvent e) {
        ClockingIn clockingIn = new ClockingIn();
            clockingIn.setCleno(textFieldeno.getText());
            clockingIn.setCldatetime(Timestamp.valueOf(textFielddatetime.getText()));
            clockingIn.setClstatus(String.valueOf(ComboxClockingin.getSelectedItem()));
            this.setInputData(clockingIn);
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
        label1 = new JLabel();
        label2 = new JLabel();
        textFieldeno = new JTextField();
        textFielddatetime = new JTextField();
        ComboxClockingin = new JComboBox();
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
            0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
            . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
            red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
            beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[fill]" +
                    "[131,fill]" +
                    "[26,fill]" +
                    "[69,fill]" +
                    "[235,fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- label1 ----
                label1.setText("\u5458\u5de5\u53f7");
                contentPanel.add(label1, "cell 1 0");

                //---- label2 ----
                label2.setText("\u8003\u52e4\u65f6\u95f4");
                contentPanel.add(label2, "cell 4 0");

                //---- textFieldeno ----
                textFieldeno.setEditable(false);
                contentPanel.add(textFieldeno, "cell 1 1 3 1");

                //---- textFielddatetime ----
                textFielddatetime.setEditable(false);
                contentPanel.add(textFielddatetime, "cell 4 1");
                contentPanel.add(ComboxClockingin, "cell 4 2");
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
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        cancelButtonMouseReleased(e);
                    }
                });
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
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textFieldeno;
    private JTextField textFielddatetime;
    private JComboBox ComboxClockingin;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
