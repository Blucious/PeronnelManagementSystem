/*
 * Created by JFormDesigner on Mon Jan 06 23:00:00 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.*;

import PMS.db.DBClockinginTime;
import net.miginfocom.swing.*;

/**
 * @author she
 */
public class SetClockinginTime extends JDialog {
    public SetClockinginTime(Window owner) {
        super(owner);
        initComponents();
        for (int i = 0; i <24 ; i++) {
            comboBoxhour.addItem(String.valueOf(i));
        }
        for (int i = 0; i <60 ; i++) {
            comboBoxminute.addItem(String.valueOf(i));
            comboBoxsecond.addItem(String.valueOf(i));
        }

    }

    private void okButtonMouseReleased(MouseEvent e) throws SQLException {
        Time time =DBClockinginTime.get();
        if (time==null){
            DBClockinginTime.add(Time.valueOf(comboBoxhour.getSelectedItem()+":"+comboBoxminute.getSelectedItem()+":"+comboBoxsecond.getSelectedItem()));
        }else {
System.out.println((Time.valueOf(comboBoxhour.getSelectedItem()+":"+comboBoxminute.getSelectedItem()+":"+comboBoxsecond.getSelectedItem())));
            DBClockinginTime.update(Time.valueOf(comboBoxhour.getSelectedItem()+":"+comboBoxminute.getSelectedItem()+":"+comboBoxsecond.getSelectedItem()));
        }
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - she
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        comboBoxhour = new JComboBox();
        label1 = new JLabel();
        comboBoxminute = new JComboBox();
        label2 = new JLabel();
        comboBoxsecond = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
            ,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red),
            dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[92,fill]" +
                    "[0,fill]" +
                    "[78,fill]" +
                    "[fill]" +
                    "[60,fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));
                contentPanel.add(comboBoxhour, "cell 0 0");

                //---- label1 ----
                label1.setText(":");
                contentPanel.add(label1, "cell 1 0");
                contentPanel.add(comboBoxminute, "cell 2 0");

                //---- label2 ----
                label2.setText(":");
                contentPanel.add(label2, "cell 3 0");
                contentPanel.add(comboBoxsecond, "cell 4 0");
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

                        try {
                            okButtonMouseReleased(e);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


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
    private JComboBox comboBoxhour;
    private JLabel label1;
    private JComboBox comboBoxminute;
    private JLabel label2;
    private JComboBox comboBoxsecond;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
