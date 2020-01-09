/*
 * Created by JFormDesigner on Mon Jan 06 23:00:00 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
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

        // 设置可选值
        for (int i = 0; i < 24; i++) {
            comboBoxhour.addItem(i);
        }
        for (int i = 0; i < 60; i++) {
            comboBoxminute.addItem(i);
            comboBoxsecond.addItem(i);
        }

        // 设置预选值
        Time t = DBClockinginTime.get();
        comboBoxsecond.setSelectedIndex(t.getSeconds());
        comboBoxminute.setSelectedIndex(t.getMinutes());
        comboBoxhour.setSelectedIndex(t.getHours());
    }

    private void okButtonMouseReleased(MouseEvent e) {
        Time time = DBClockinginTime.get();
        if (time == null) {
            DBClockinginTime.add(Time.valueOf(comboBoxhour.getSelectedItem() + ":" + comboBoxminute.getSelectedItem() + ":" + comboBoxsecond.getSelectedItem()));
        } else {
            System.out.println((Time.valueOf(comboBoxhour.getSelectedItem() + ":" + comboBoxminute.getSelectedItem() + ":" + comboBoxsecond.getSelectedItem())));
            DBClockinginTime.update(Time.valueOf(comboBoxhour.getSelectedItem() + ":" + comboBoxminute.getSelectedItem() + ":" + comboBoxsecond.getSelectedItem()));
        }

        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        comboBoxhour = new JComboBox<>();
        label1 = new JLabel();
        comboBoxminute = new JComboBox<>();
        label2 = new JLabel();
        comboBoxsecond = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("\u4fee\u6539\u4e0a\u73ed\u65f6\u95f4");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
                okButton.setText("\u4fee\u6539");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
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
    private JComboBox<Integer> comboBoxhour;
    private JLabel label1;
    private JComboBox<Integer> comboBoxminute;
    private JLabel label2;
    private JComboBox<Integer> comboBoxsecond;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
