package PMS.security;

import java.util.Random;

public class VerificationCodeUtil {

    // 字符表
    public final static String CHAR_TABLE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // 默认长度
    public final static int LENGTH_DEFAULT = 7;


    public static Random getRandom() {
        return new Random();
    }

    public static String generate() {
        return generate(LENGTH_DEFAULT, null);
    }

    public static String generate(int length) {
        return generate(length, null);
    }

    public static String generate(Random random) {
        return generate(LENGTH_DEFAULT, random);
    }

    public static String generate(int length, Random random) {
        if (length < 0) {
            return null;
        }

        if (random == null) {
            random = getRandom();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHAR_TABLE.length());
            char ch = CHAR_TABLE.charAt(index);
            sb.append(ch);
        }

        return sb.toString();
    }

    // 测试
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generate());
        }
    }
}
