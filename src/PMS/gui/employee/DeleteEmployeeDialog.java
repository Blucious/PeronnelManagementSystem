/*
 * Created by JFormDesigner on Fri Jan 03 09:26:51 CST 2020
 */

package PMS.gui.employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.db.DBEmployee;
import PMS.gui.com.DataInputDialog;
import PMS.gui.model.TableModel;
//import net.miginfocom.swing.*;

/**
 * @author she
 */
public class DeleteEmployeeDialog extends DataInputDialog {
    // 表数据模型
    TableModel tableModel;

    public DeleteEmployeeDialog(Window owner) {
        super(owner);
        initComponents();

        tableModel = new TableModel("SELECT * FROM employee WHERE 0=1");
        tableEmployee.setModel(tableModel);
    }

    private void okButtonMouseClicked(MouseEvent e) {
    }

    private void selectbuttonMouseReleased(MouseEvent e) {
        String[] nosToDelete = textFieldDeleteEmployeeNo.getText().split(";");
        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT * FROM employee WHERE empNo in (");
//        for (int i = 0; i < nosToDelete.length; i++) {
//            if (i != nosToDelete.length - 1)
//                sql.append("?,");
//            else
//                sql.append("?");
//        }
//        sql.append(")");


        sql.append("select * from employee where empNo=" + "'" + nosToDelete[0] + "'" + "or empNo=");
        if (nosToDelete.length != 0) {
            for (int i = 1; i < nosToDelete.length; i++) {
                sql.append("'" + nosToDelete[i] + "'" + "or empNo=");
//            String sql ="select * from employee where empNo="+"'"+deleno[i]+"'";
            }

        }
        sql.append("''");

        System.out.println(sql.toString()); // 调试

        tableModel.setQuery(sql.toString());
    }

    private void okButtonMouseReleased(MouseEvent e) {
        // 设置输入数据
        setInputData(textFieldDeleteEmployeeNo.getText());

        // 设置窗口为不可见，从而使调用者停止阻塞
        this.setVisible(false);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelNotice = new JLabel();
        textFieldDeleteEmployeeNo = new JTextField();
        selectButton = new JButton();
        scrollPane = new JScrollPane();
        tableEmployee = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 127, 301, 113, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0E-4};

                //---- labelNotice ----
                labelNotice.setText("\u5458\u5de5\u53f7\uff1a\u591a\u4e2a\uff1b\u5206\u9694");
                contentPanel.add(labelNotice, new GridBagConstraints(1, 0, 5, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 7, 7), 0, 0));
                contentPanel.add(textFieldDeleteEmployeeNo, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 7, 7), 0, 0));

                //---- selectButton ----
                selectButton.setText("\u67e5\u627e");
                selectButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        selectbuttonMouseReleased(e);
                    }
                });
                contentPanel.add(selectButton, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 7, 7), 0, 0));

                //======== scrollPane ========
                {

                    //---- tableEmployee ----
                    tableEmployee.setMaximumSize(new Dimension(32, 32));
                    scrollPane.setViewportView(tableEmployee);
                }
                contentPanel.add(scrollPane, new GridBagConstraints(1, 1, 6, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 7), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(null);

                //---- okButton ----
                okButton.setText("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        okButtonMouseReleased(e);
                    }
                });
                buttonBar.add(okButton);
                okButton.setBounds(new Rectangle(new Point(354, 11), okButton.getPreferredSize()));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton);
                cancelButton.setBounds(new Rectangle(new Point(439, 11), cancelButton.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < buttonBar.getComponentCount(); i++) {
                        Rectangle bounds = buttonBar.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = buttonBar.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    buttonBar.setMinimumSize(preferredSize);
                    buttonBar.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(530, 355);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelNotice;
    private JTextField textFieldDeleteEmployeeNo;
    private JButton selectButton;
    private JScrollPane scrollPane;
    private JTable tableEmployee;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
