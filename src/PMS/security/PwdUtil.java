package PMS.security;

/*
 * 参考:
 *      jBCrypt官网 —— http://www.mindrot.org/projects/jBCrypt/
 *      Java通过BCrypt加密 —— https://www.cnblogs.com/xingzc/p/8624007.html
 */

// 密码工具类PwdUtil，实现密码的摘要计算、和验证功能
public class PwdUtil {

    //私有空构造方法,保证本类不能够被实例化。
    private PwdUtil() {
    }

    // 计算密码的数字摘要
    public static String hashPwd(String pw) {
        // bcrypt加密后的字符串形如：
        //     $2a$10$asdjflkaydgigadfahgl.asdfaoygoqhgasldhf，
        // 其中：
        //     是分割符，无意义；
        //     2a是bcrypt加密版本号；
        //     10是cost的值；
        //     而后的前22位是salt值；
        //     再然后的字符串就是密码的密文了；

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pw, salt);
    }

    public static String hashPwd(char[] password) {
        return hashPwd(new String(password));
    }

    // 密码验证
    public static boolean checkPwd(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }

    public static boolean checkPwd(char[] plaintext, String hashed) {
        return checkPwd(new String(plaintext), hashed);
    }

    // PwdUtil测试
    public static void main(String[] args) {
        String a = hashPwd("123");
        boolean r = BCrypt.checkpw("123", a);

        assert r;

        System.out.println(a);
        System.out.println(r);
    }
}
