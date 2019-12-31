package PMS.bean;

import PMS.security.PwdUtil;

// 人事管理系统账号类
//    该类尽量保证password属性存储的值为摘要值，
//    但是无法完全保证，该类的使用者需将构造函数的passwordIsHashed参数的值设置正确
// 此外，该类不进行数据正确性验证，正确性交由其余代码和数据库处理
public abstract class Account {

    private String name;
    private String hashedPassword;

    public Account(String name, char[] passwordPlaintext, boolean passwordIsHashed) {
        this(name, new String(passwordPlaintext), passwordIsHashed);
    }

    public Account(String name, String password, boolean passwordIsHashed) {
        setName(name);

        if (passwordIsHashed) { // 判断是否需要加密
            setHashedPassword(password);
        } else {
            setPasswordAndHash(password);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPasswordAndHash(String passwordPlaintext) {
        setHashedPassword(PwdUtil.hashPwd(passwordPlaintext));
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
