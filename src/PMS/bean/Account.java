package PMS.bean;

import PMS.security.PwdUtil;

// ���¹���ϵͳ�˺���
//    ���ྡ����֤password���Դ洢��ֵΪժҪֵ��
//    �����޷���ȫ��֤�������ʹ�����轫���캯����passwordIsHashed������ֵ������ȷ
// ���⣬���಻����������ȷ����֤����ȷ�Խ��������������ݿ⴦��
public abstract class Account {

    private String name;
    private String hashedPassword;

    public Account(String name, char[] passwordPlaintext, boolean passwordIsHashed) {
        this(name, new String(passwordPlaintext), passwordIsHashed);
    }

    public Account(String name, String password, boolean passwordIsHashed) {
        setName(name);

        if (passwordIsHashed) { // �ж��Ƿ���Ҫ����
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
