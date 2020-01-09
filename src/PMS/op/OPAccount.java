package PMS.op;

import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.security.PasswordUtil;

import java.util.Arrays;


public abstract class OPAccount {
    private OPAccount() {
    }

    /**
     * 进行账号校验，如果成功返回对应的Account，否则返回null
     */
    public static Account loginVerify(String name, char[] passwordPlaintext) {
        // password 用String存放不安全（全局静态变量区），应该用char[]，用完之后直接全部置零。

        // 查询输入的用户名对应的账户
        Account account = DBAccount.get(name);

        // 如果账户存在，且数字摘要计算结果相同，则验证通过
        boolean isVerified = (account != null &&
                PasswordUtil.checkPwd(passwordPlaintext, account.getHashedPassword()));

        return isVerified ? account : null;
    }

    public static class Result {
        public boolean state = false; // 默认false
        public String message;
    }

    /**
     * 验证账户名和密码的有效性
     * true合法，否则非法
     */
    public static Result isValid(
            String name, char[] passwordPlaintext, char[] passwordPlaintextAgain) {
        Result r = new Result();

        // 账户名不允许为空
        if (name.length() > 0) {
            // 两次输入的密码必须相同
            if (Arrays.equals(passwordPlaintext, passwordPlaintextAgain)) {
                // 密码评估必须大于50分
                PasswordUtil.EvaluationResult er = PasswordUtil.evaluatePwd(passwordPlaintext);
                if (er.score >= 50) {
                    // 先前必须不存在相同的账户名
                    if (DBAccount.get(name) == null) {
                        r.state = true;
                        r.message = "合法";
                    } else {
                        r.message = "账户已存在";
                    }
                } else {
                    r.message = "密码强度未达到标准";
                }
            } else {
                r.message = "两次输入的密码必须相同";
            }
        } else {
            r.message = "账户名不允许为空";
        }

        return r;
    }

    public static Result register(
            String name, char[] passwordPlaintext, char[] passwordPlaintextAgain) {
        Result r = isValid(name, passwordPlaintext, passwordPlaintextAgain);
        if (r.state) { // 判断账户是否合法
            // 添加新用户
            Account account = new Account(
                    name, new String(passwordPlaintext), false);
            r.state = DBAccount.add(account);
            // 即使在验证时，确认过没有重复的账户，但是还是存在竞争问题，因为中间还有一段时间
            if (r.state) {
                r.message = "注册成功";
            } else {
                r.message = "账户已存在";
            }
        }

        return r;
    }

//    public static Result modify(String name, Account account) {
//
//    }
}
