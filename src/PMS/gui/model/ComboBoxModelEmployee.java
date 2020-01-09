package PMS.gui.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import PMS.entity.Employee;

public class ComboBoxModelEmployee extends AbstractListModel<String>
        implements javax.swing.ComboBoxModel<String> {

    private Employee selectedItem;
    private List<Employee> list;

    public ComboBoxModelEmployee(List<Employee> list) {
        setList(list);
    }

    public ComboBoxModelEmployee(Iterator<Employee> it) {
        setIterator(it);
    }

    public void setList(List<Employee> list) {
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }

        if (this.list.size() > 0) {
            setSelectedIndex(0);
        }
    }

    public void setIterator(Iterator<Employee> it) {
        list = new ArrayList<>();
        if (it != null) {
            while (it.hasNext()) {
                Employee e = it.next();
                list.add(e);
            }
        }

        if (list.size() > 0) {
            setSelectedIndex(0);
        }
    }

    public void setSelectedIndex(int index) {
        setSelectedItem(represention(list.get(index)));
    }

    public String represention(Employee e) {
        return e.getName() + "-" + e.getNo();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        for (Employee e : list) {
            if (anItem.equals(represention(e))) {
                selectedItem = e;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }

    public void setOriginalSelectedItem(Employee anItem) {
        for (Employee e : list) {
            if (anItem.equals(e)) {
                selectedItem = e;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }

    public void setOriginalSelectedItem(String empNo) {
        for (Employee e : list) {
            if (empNo.equals(e.getNo())) {
                selectedItem = e;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }

    @Override
    public Object getSelectedItem() {
        String result;

        if (selectedItem == null) {
            result = "";
        } else {
            result = represention(selectedItem);
        }

        return result;
    }

    public Employee getOriginalSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public String getElementAt(int index) {
        Employee e = list.get(index);
        return represention(e);
    }
}
