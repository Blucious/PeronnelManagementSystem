package PMS.gui.model;

import PMS.entity.Employee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ComboBoxModel<T> extends AbstractListModel<String>
        implements javax.swing.ComboBoxModel<String> {

    protected T selectedItem;
    protected List<T> values;

    public ComboBoxModel(List<T> list) {
        setList(list);
    }

    public ComboBoxModel(Iterator<T> it) {
        setIterator(it);
    }


    public void setList(List<T> list) {
        if (list != null) {
            values = list;
        } else {
            values = new ArrayList<>();
        }

        setSelectedIndex(0);
    }

    public void setIterator(Iterator<T> it) {
        values = new ArrayList<>();
        if (it != null) {
            while (it.hasNext()) {
                T obj = it.next();
                values.add(obj);
            }
        }

        setSelectedIndex(0);
    }

    public void setSelectedIndex(int index) {
        if (index > 0 && index < values.size()) {
            setSelectedItem(represention(values.get(index)));
        }
    }

    public abstract String represention(T o);

    @Override
    public void setSelectedItem(Object anItem) {
        for (T obj : values) {
            if (anItem.equals(represention(obj))) {
                selectedItem = obj;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }

    public void setOriginalSelectedItem(T anItem) {
        for (T obj : values) {
            if (anItem.equals(obj)) {
                selectedItem = obj;
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

    public T getOriginalSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return values.size();
    }

    @Override
    public String getElementAt(int index) {
        T obj = values.get(index);
        return represention(obj);
    }
}
