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
            return String.format("ǿ��(%d) - %s", score, description);
        }
    }

    // ��������
    public static EvaluationResult evaluatePwd(char[] passwordPlaintext) {
        // �ο���
        // ˵˵����ǿ�ȹ��� ���� https://zhuanlan.zhihu.com/p/25545606

        EvaluationResult er = new EvaluationResult();

        // ���볤��
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

        // ��ĸ
        if ((uppercaseCount > 0 && lowercaseCount == 0)
                || (uppercaseCount == 0 && lowercaseCount > 0)) {
            er.score += 10;
        } else if (uppercaseCount > 0 && lowercaseCount > 0) {
            er.score += 20;
        }

        // ����
        if (digitCount >= 3) {
            er.score += 20;
        } else if (digitCount >= 1) {
            er.score += 10;
        }

        // ����
        if (otherCount > 1) {
            er.score += 25;
        } else if (otherCount == 1) {
            er.score += 10;
        }

        // ����
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
            er.description = "�ǳ�ǿ";
        } else if (er.score >= 60) {
            er.description = "ǿ";
        } else if (er.score >= 50) {
            er.description = "һ��";
        } else {
            er.description = "��";
        }

        return er;
    }

    // PwdUtil����
    public static void main(String[] args) {
        String a = hashPwd("admin");
        boolean r = BCrypt.checkpw("admin", a);

        assert r;

        System.out.println(a);
        System.out.println(r);
    }
}
