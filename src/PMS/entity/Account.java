package PMS.entity;

import PMS.security.PasswordUtil;

import java.io.Serializable;

// 人事管理系统账号类
//
// 该类在设计上，不允许存储密码的明文，它尽量保证 hashedPassword 属性存储的值为摘要值，
//    但是无法完全保证，该类的使用者需传递正确的参数
// 此外，该类不进行数据正确性验证
public class Account
        implements Serializable {

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
                null, Privilege.PRIVILEGE_DEFAULT); // 设置默认权限
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
        return getPrivilege().equals(Privilege.PRIVILEGE_ADMIN);
    }

    public boolean isPersonnelStaff() {
        return getPrivilege().equals(Privilege.PRIVILEGE_PERSONNEL_STAFF);
    }

    public boolean isNormalStaff() {
        return getPrivilege().equals(Privilege.PRIVILEGE_NORMAL_STAFF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (empNo != null ? !empNo.equals(account.empNo) : account.empNo != null) return false;
        return privilege != null ? privilege.equals(account.privilege) : account.privilege == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (empNo != null ? empNo.hashCode() : 0);
        result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
        return result;
    }
}
