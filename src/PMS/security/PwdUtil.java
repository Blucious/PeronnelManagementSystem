package PMS.security;

/*
 * �ο�:
 *      jBCrypt���� ���� http://www.mindrot.org/projects/jBCrypt/
 *      Javaͨ��BCrypt���� ���� https://www.cnblogs.com/xingzc/p/8624007.html
 */

// ���빤����PwdUtil��ʵ�������ժҪ���㡢����֤����
public class PwdUtil {

    //˽�пչ��췽��,��֤���಻�ܹ���ʵ������
    private PwdUtil() {
    }

    // �������������ժҪ
    public static String hashPwd(String pw) {
        // bcrypt���ܺ���ַ������磺
        //     $2a$10$asdjflkaydgigadfahgl.asdfaoygoqhgasldhf��
        // ���У�
        //     �Ƿָ���������壻
        //     2a��bcrypt���ܰ汾�ţ�
        //     10��cost��ֵ��
        //     �����ǰ22λ��saltֵ��
        //     ��Ȼ����ַ�����������������ˣ�

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pw, salt);
    }

    public static String hashPwd(char[] password) {
        return hashPwd(new String(password));
    }

    // ������֤
    public static boolean checkPwd(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }

    public static boolean checkPwd(char[] plaintext, String hashed) {
        return checkPwd(new String(plaintext), hashed);
    }

    // PwdUtil����
    public static void main(String[] args) {
        String a = hashPwd("123");
        boolean r = BCrypt.checkpw("123", a);

        assert r;

        System.out.println(a);
        System.out.println(r);
    }
}
