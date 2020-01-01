package PMS.op;

import PMS.db.DBAccount;
import PMS.entity.Account;
import PMS.security.PwdUtil;


public abstract class OPAccount {
    private OPAccount(){}

    public static class LoginResult {
        boolean state; // 登录成功true，否则false
        String message;

        public LoginResult() {
        }

        public LoginResult(boolean state, String message) {
            this.state = state;
            this.message = message;
        }
    }

    public static LoginResult login(String name, String password) {
        // password 用String存放不安全（全局静态变量区），应该用char[]，用完之后直接全部置零。

        LoginResult lr = new LoginResult();

        // 查询输入的用户名对应的账户
        Account account = DBAccount.getAccount(name);

        // 比较数字摘要
        if (account != null &&
                PwdUtil.checkPwd(password, account.getHashedPassword())) {
            lr.state = true;
            lr.message = "登录成功";
        } else {
            lr.state = false;
            lr.message = "账户或密码错误";
        }

        return lr;
    }
}
