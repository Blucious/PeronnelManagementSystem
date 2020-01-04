package PMS.entity;

import PMS.security.PasswordUtil;

// 人事管理系统账号类
//
// 该类在设计上，不允许存储密码的明文，它尽量保证 hashedPassword 属性存储的值为摘要值，
//    但是无法完全保证，该类的使用者需传递正确的参数
// 此外，该类不进行数据正确性验证
public class Account {

    // 权限值
    private final static String PRIVILEGE_ADMIN = "管理员"; // 管理员权限
    private final static String PRIVILEGE_PERSONNEL_STAFF = "人事员工"; // 人事员工权限
    private final static String PRIVILEGE_NORMAL_STAFF = "普通员工"; // 普通员工权限

    private final static String PRIVILEGE_DEFAULT = PRIVILEGE_NORMAL_STAFF; // 默认权限


    // 实例属性
    private String name;
    private String hashedPassword;
    private String empNo;
    private String privilege;

    public Account() {
    }

    public Account(
            String name,
            String password, boolean passwordIsHashed) {
        this(name, password, passwordIsHashed,
                null, PRIVILEGE_DEFAULT); // 设置默认权限
    }

    public Account(
            String name,
            String password, boolean passwordIsHashed,
            String empNo,
            String privilege) {
        setName(name);
        setHashedPassword(password, passwordIsHashed); // passwordIsHashed表明密码是否已经被摘要
        setEmpNo(empNo);
        setPrivilege(privilege);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHashedPassword(String password, boolean isHashed) {
        if (!isHashed) // 如果密码为明文，则转换成摘要
            password = PasswordUtil.hashPwd(password);
        this.hashedPassword = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", empNo='" + empNo + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }

    // 权限判断便利方法
    public boolean isAdmin() {
        return getPrivilege().equals(PRIVILEGE_ADMIN);
    }

    public boolean isPersonnelStaff() {
        return getPrivilege().equals(PRIVILEGE_PERSONNEL_STAFF);
    }

    public boolean isNormalStaff() {
        return getPrivilege().equals(PRIVILEGE_NORMAL_STAFF);
    }
}
