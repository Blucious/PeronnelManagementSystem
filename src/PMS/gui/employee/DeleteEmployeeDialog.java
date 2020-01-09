/*
 * Created by JFormDesigner on Thu Jan 09 16:00:21 CST 2020
 */

package PMS.gui.employee;

import PMS.gui.model.TableModel;
import PMS.gui.com.DataInputDialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author c
 */
public class DeleteEmployeeDialog extends DataInputDialog {

    // 表数据模型
    private TableModel tableModelEmployee;

    public DeleteEmployeeDialog(Window owner) {
        super(owner);
        initComponents();

        tableModelEmployee = new TableModel();
        tableEmployee.setModel(tableModelEmployee);
        searchEmployeeByName("%");
    }

    private void searchEmployeeByName(String name) {
        String sql = "SELECT empNo 员工号, empName 员工名, depName 所属部门, empTitle 头衔 " +
                "FROM employee LEFT JOIN department ON (empDepNo=depNo) " +
                "WHERE empName LIKE ?";
        tableModelEmployee.setQuery(sql, name);
    }

    private void searchButtonMouseClicked(MouseEvent e) {
        String wildcard = textFieldWildcard.getText();
        searchEmployeeByName(wildcard);
    }

    static class Result {
        public List<String> empNoList; // 要删除的员工列表
    }

    private void okButtonMouseClicked(MouseEvent e) {
        int[] selectedRows = tableEmployee.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "未选择员工",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int opt = JOptionPane.showConfirmDialog(null,
                String.format("确认删除选中的%d个员工吗？操作不可逆！", selectedRows.length),
                "提示", JOptionPane.OK_CANCEL_OPTION);

        if (opt == JOptionPane.OK_OPTION) {

            Result r = new Result();
            r.empNoList = new ArrayList<>(selectedRows.length);
            for (int selectedRow : selectedRows) {
                r.empNoList.add((String) tableEmployee.getValueAt(selectedRow, 0));
            }
            setInputData(r);
        }

        setVisible(false);
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        setVisible(false);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelNotice = new JLabel();
        textFieldWildcard = new JTextField();
        searchButton = new JButton();
        scrollPane1 = new JScrollPane();
        tableEmployee = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u5220\u9664\u5458\u5de5");
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 105, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0, 1.0, 1.0E-4};

                //---- labelNotice ----
                labelNotice.setText("\u5458\u5de5\u540d\uff1a");
                contentPanel.add(labelNotice, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(textFieldWildcard, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchButton ----
                searchButton.setText("\u67e5\u627e\uff08\u901a\u914d\u7b26'%'\u3001'_'\uff09");
                searchButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        searchButtonMouseClicked(e);
                    }
                });
                contentPanel.add(searchButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(tableEmployee);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 4, 3, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u5220\u9664");
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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelNotice;
    private JTextField textFieldWildcard;
    private JButton searchButton;
    private JScrollPane scrollPane1;
    private JTable tableEmployee;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
