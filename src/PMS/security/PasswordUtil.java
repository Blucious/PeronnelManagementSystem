package PMS.security;

/*
 * 参考:
 *      jBCrypt官网 —— http://www.mindrot.org/projects/jBCrypt/
 *      Java通过BCrypt加密 —— https://www.cnblogs.com/xingzc/p/8624007.html
 */

// 密码工具类PwdUtil，实现密码的摘要计算、和验证功能
public class PasswordUtil {

    //私有空构造方法,保证本类不能够被实例化。
    private PasswordUtil() {
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


    public static class EvaluationResult {
        public int score;
        public String description;

        public EvaluationResult() {
        }

        public EvaluationResult(int score, String description) {
            this.score = score;
            this.description = description;
        }

        @Override
        public String toString() {
            return String.format("强度(%d) - %s", score, description);
        }
    }

    // 评估密码
    public static EvaluationResult evaluatePwd(char[] passwordPlaintext) {
        // 参考：
        // 说说密码强度规则 —— https://zhuanlan.zhihu.com/p/25545606

        EvaluationResult er = new EvaluationResult();

        // 密码长度
        if (passwordPlaintext.length >= 8) {
            er.score += 25;
        } else if (passwordPlaintext.length >= 5) {
            er.score += 10;
        } else if (passwordPlaintext.length >= 1) {
            er.score += 5;
        }

        int uppercaseCount = 0;
        int lowercaseCount = 0;
        int digitCount = 0;
        int otherCount = 0;

        for (int i = 0; i < passwordPlaintext.length; i++) {
            char ch = passwordPlaintext[i];
            if (Character.isUpperCase(ch)) {
                uppercaseCount++;
            } else if (Character.isLowerCase(ch)) {
                lowercaseCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            } else {
                otherCount++;
            }
        }

        // 字母
        if ((uppercaseCount > 0 && lowercaseCount == 0)
                || (uppercaseCount == 0 && lowercaseCount > 0)) {
            er.score += 10;
        } else if (uppercaseCount > 0 && lowercaseCount > 0) {
            er.score += 20;
        }

        // 数字
        if (digitCount >= 3) {
            er.score += 20;
        } else if (digitCount >= 1) {
            er.score += 10;
        }

        // 其它
        if (otherCount > 1) {
            er.score += 25;
        } else if (otherCount == 1) {
            er.score += 10;
        }

        // 奖励
        if (digitCount > 0 && (uppercaseCount + lowercaseCount) > 0) {
            if (otherCount > 0) {
                if ( uppercaseCount > 0 && lowercaseCount > 0) {
                    er.score += 5;
                }
                er.score += 3;
            }
            er.score += 2;
        }

        if (er.score >= 80) {
            er.description = "非常强";
        } else if (er.score >= 60) {
            er.description = "强";
        } else if (er.score >= 50) {
            er.description = "一般";
        } else {
            er.description = "弱";
        }

        return er;
    }

    // PwdUtil测试
    public static void main(String[] args) {
        String a = hashPwd("admin");
        boolean r = BCrypt.checkpw("admin", a);

        assert r;

        System.out.println(a);
        System.out.println(r);
    }
}
