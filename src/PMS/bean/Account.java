package PMS.bean;

import PMS.security.PwdUtil;

// ���¹���ϵͳ�˺���
//    ���ྡ����֤password���Դ洢��ֵΪժҪֵ��
//    �����޷���ȫ��֤�������ʹ�����轫���캯����passwordIsHashed������ֵ������ȷ
public class Account {

    private final String name;
    private final String hashedPassword;

    public Account(String name, char[] passwordPlaintext, boolean passwordIsHashed) {
        this(name, new String(passwordPlaintext), passwordIsHashed);
    }

    public Account(String name, String passwordPlaintext, boolean passwordIsHashed) {
        this.name = name;
        // ���passwordIsHashedΪfalse������м���
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
