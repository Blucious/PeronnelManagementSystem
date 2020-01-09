package PMS.gui.model;

import PMS.entity.Title;

import java.util.Iterator;
import java.util.List;

public class ComboBoxModelTitle extends ComboBoxModel<Title> {

    public ComboBoxModelTitle(List<Title> list) {
        super(list);
    }

    public ComboBoxModelTitle(Iterator<Title> it) {
        super(it);
    }

    public String represention(Title t) {
        return t.getName();
    }

    public void setSelectedItemByName(String name) {
        for (Title t : values) {
            if (name.equals(t.getName())) {
                selectedItem = t;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }
}
