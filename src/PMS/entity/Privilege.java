package PMS.entity;

public class Privilege {
    // 权限值
    public final static String PRIVILEGE_ADMIN = "管理员"; // 管理员权限
    public final static String PRIVILEGE_PERSONNEL_STAFF = "人事员工"; // 人事员工权限
    public final static String PRIVILEGE_NORMAL_STAFF = "普通员工"; // 普通员工权限

    public final static String PRIVILEGE_DEFAULT = PRIVILEGE_NORMAL_STAFF; // 默认权限


    private String name;

    public Privilege(){
    }

    public Privilege(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "name='" + name + '\'' +
                '}';
    }


    // 权限判断便利方法
    public boolean isAdmin() {
        return getName().equals(Privilege.PRIVILEGE_ADMIN);
    }

    public boolean isPersonnelStaff() {
        return getName().equals(Privilege.PRIVILEGE_PERSONNEL_STAFF);
    }

    public boolean isNormalStaff() {
        return getName().equals(Privilege.PRIVILEGE_NORMAL_STAFF);
    }
}
