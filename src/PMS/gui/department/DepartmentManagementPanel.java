/*
 * Created by JFormDesigner on Wed Jan 08 11:25:37 CST 2020
 */

package PMS.gui.department;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PMS.db.DBDepartment;
import PMS.entity.Department;
import PMS.gui.model.TableModel;

import net.miginfocom.swing.*;


/**
 * @author she
 */
public class DepartmentManagementPanel extends JPanel {
    private TableModel tableModeldept;
    public DepartmentManagementPanel() {
        initComponents();

        String sql = "select depNo as '部门号',depName as '部门名' from department ";
        tableModeldept = new TableModel(sql);
        tableDept.setModel(tableModeldept);
//        tableModeldept.setQuery(sql);

    }



    private void button1MouseReleased(MouseEvent e) {
        AddingDepartmentDialog addingDepartmentDialog = new AddingDepartmentDialog(null);
        addingDepartmentDialog.setVisible(true);
        addingDepartmentDialog.dispose();
        Department department = (Department)addingDepartmentDialog.getInputData();
        if (department!=null){
            DBDepartment.add(department);
            tableModeldept.setQuery("select depNo as '部门号',depName as '部门名' from department ");
        }

    }

    private void buttonModifyMouseReleased(MouseEvent e) {
        ModifyDepartmentDialog modifyDepartmentDialog = new ModifyDepartmentDialog(null);
        modifyDepartmentDialog.setVisible(true);
        modifyDepartmentDialog.dispose();
        if (modifyDepartmentDialog.getInputData()!=null){
        if (modifyDepartmentDialog.getInputData() instanceof Department){
            Department department = (Department)modifyDepartmentDialog.getInputData();
            DBDepartment.update(department.getNo(),department);
            tableModeldept.setQuery("select depNo as '部门号',depName as '部门名' from department ");
        }else {
            String deleteno = String.valueOf(modifyDepartmentDialog.getInputData());
            DBDepartment.delete(deleteno);
            tableModeldept.setQuery("select depNo as '部门号',depName as '部门名' from department ");
        }
        }
    }



    private void tableDeptMouseClicked(MouseEvent e) {
        int line =tableDept.getSelectedRow();
        String dnovalue= String.valueOf(tableDept.getValueAt(line,0));
        ModifyDepartmentDialog modifyDepartmentDialog = new ModifyDepartmentDialog(null,dnovalue);
        modifyDepartmentDialog.setVisible(true);
        modifyDepartmentDialog.dispose();
        if (modifyDepartmentDialog.getInputData()!=null){
            if (modifyDepartmentDialog.getInputData() instanceof Department){
                Department department = (Department)modifyDepartmentDialog.getInputData();
                DBDepartment.update(department.getNo(),department);
                tableModeldept.setQuery("select depNo as '部门号',depName as '部门名' from department ");
            }else {
                String deleteno = String.valueOf(modifyDepartmentDialog.getInputData());
                DBDepartment.delete(deleteno);
                tableModeldept.setQuery("select depNo as '部门号',depName as '部门名' from department ");
            }
        }
    }

    private void tableEmployeeMouseReleased(MouseEvent e) {
        // TODO add your code here
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - c
        panel1 = new JPanel();
        buttonAdd = new JButton();
        buttonModify = new JButton();
        scrollPane1 = new JScrollPane();
        tableDept = new JTable();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {10, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {10, 0, 5, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- buttonAdd ----
            buttonAdd.setText("\u6dfb\u52a0");
            buttonAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    button1MouseReleased(e);
                }
            });
            panel1.add(buttonAdd, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- buttonModify ----
            buttonModify.setText("\u4fee\u6539");
            buttonModify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    buttonModifyMouseReleased(e);
                }
            });
            panel1.add(buttonModify, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
        }
        add(panel1);

        //======== scrollPane1 ========
        {

            //---- tableDept ----
            tableDept.setMaximumSize(null);
            tableDept.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tableDeptMouseClicked(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    tableEmployeeMouseReleased(e);
                }
            });
            scrollPane1.setViewportView(tableDept);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - c
    private JPanel panel1;
    private JButton buttonAdd;
    private JButton buttonModify;
    private JScrollPane scrollPane1;
    private JTable tableDept;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
