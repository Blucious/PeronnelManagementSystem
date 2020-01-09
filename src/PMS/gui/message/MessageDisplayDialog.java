/*
 * Created by JFormDesigner on Sun Jan 05 16:51:13 CST 2020
 */

package PMS.gui.message;

import PMS.gui.com.DataInputDialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author c
 */
public class MessageDisplayDialog extends DataInputDialog {
    String text;

    public MessageDisplayDialog(Window owner, String text) {
        super(owner);
        initComponents();

        textPane1.setText(text);
    }

    class Result {
        boolean doReplay; // 是否回复
    }

    private void okButtonMouseReleased(MouseEvent e) {
        setVisible(false);
    }

    private void replybuttonMouseClicked(MouseEvent e) {
        Result r = new Result();
        r.doReplay = true;
        setInputData(r);

        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        replybutton = new JButton();
        okButton = new JButton();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();

        //======== this ========
        setModal(true);
        setTitle("\u6d88\u606f\u8be6\u60c5");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
            awt.Font.BOLD,12),java.awt.Color.red),dialogPane. getBorder()))
            ;dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- replybutton ----
                replybutton.setText("\u56de\u590d");
                replybutton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        replybuttonMouseClicked(e);
                    }
                });
                buttonBar.add(replybutton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText("\u786e\u8ba4");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //======== scrollPane1 ========
            {

                //---- textPane1 ----
                textPane1.setEditable(false);
                scrollPane1.setViewportView(textPane1);
            }
            dialogPane.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(430, 325);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton replybutton;
    private JButton okButton;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
