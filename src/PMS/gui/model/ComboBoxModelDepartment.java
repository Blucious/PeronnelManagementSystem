package PMS.gui.model;

import PMS.entity.Department;

import java.util.Iterator;
import java.util.List;

public class ComboBoxModelDepartment extends ComboBoxModel<Department> {

    public ComboBoxModelDepartment(List<Department> list) {
        super(list);
    }

    public ComboBoxModelDepartment(Iterator<Department> it) {
        super(it);
    }

    public String represention(Department d) {
        return d.getName() + "-" + d.getNo();
    }

    public void setSelectedItemByNo(String no) {
        for (Department d : values) {
            if (no.equals(d.getNo())) {
                selectedItem = d;
                break;
            }
        }
        fireContentsChanged(this, -1, -1);
    }
}
