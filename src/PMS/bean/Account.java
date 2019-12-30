package PMS.bean;

import PMS.security.PwdUtil;

// 人事管理系统账号类
//    该类尽量保证password属性存储的值为摘要值，
//    但是无法完全保证，该类的使用者需将构造函数的passwordIsHashed参数的值设置正确
public class Account {

    private final String name;
    private final String hashedPassword;

    public Account(String name, char[] passwordPlaintext, boolean passwordIsHashed) {
        this(name, new String(passwordPlaintext), passwordIsHashed);
    }

    public Account(String name, String passwordPlaintext, boolean passwordIsHashed) {
        this.name = name;
        // 如果passwordIsHashed为false，则进行加密
        this.hashedPassword = passwordIsHashed ? passwordPlaintext :
                PwdUtil.hashPwd(passwordPlaintext);
    }

    public String getName() {
        return name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
