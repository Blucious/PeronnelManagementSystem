package PMS.op;

import PMS.db.DBEmployee;
import PMS.db.DBDepartment;
import PMS.db.DBTitle;
import PMS.entity.Employee;

import javax.swing.*;
import java.sql.Date;

public class OPEmployee {

    public static Employee modifyIsValid(
            Employee empOriginal,
            String no, String name, String birthdayString, String depNo, String title) {

        // 进行数值检查
        // 员工号
        if (no.length() == 0) {
            JOptionPane.showMessageDialog(null, "名字不能为空",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (empOriginal != null) {
            if (!empOriginal.getNo().equals(no) && DBEmployee.get(no) != null) {
                JOptionPane.showMessageDialog(null, "员工号已存在",
                        "错误", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        // 员工名
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "名字不能为空",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 员工生日
        Date birthday;
        try {
            birthday = Date.valueOf(birthdayString);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, "日期格式错误，应为yyyy-m[m]-d[d]",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 员工所属部门
        if (DBDepartment.get(depNo) == null) {
            JOptionPane.showMessageDialog(null, "部门号错误",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 员工头衔
        if (DBTitle.get(title) == null) {
            JOptionPane.showMessageDialog(null, "头衔错误",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // 当所有值合法后，创建员工实体
        Employee emp = new Employee();
        emp.setNo(no);
        emp.setName(name);
        emp.setBirthday(birthday);
        emp.setDepNo(depNo);
        emp.setTitle(title);

        return emp;
    }
}
