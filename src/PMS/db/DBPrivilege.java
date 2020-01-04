package PMS.db;

import PMS.db.util.DBAccessUtil;
import PMS.db.util.ResultSetHandler;
import PMS.entity.Privilege;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


// 实现权限表的增删改查操作
public final class DBPrivilege {

    //私有空构造方法,保证本类不能够被实例化。
    private DBPrivilege() {
    }

    /**
     * 添加
     */
    public static boolean add(Privilege privilege) {
        // 查询语句
        String sql = "INSERT INTO privilege (priName) " +
                "VALUES (?)";
        // 执行查询
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, privilege.getName());
        // 异常处理
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 删除
     */
    public static boolean delete(String name) {
        String sql = "DELETE FROM privilege WHERE priName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql, name);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 修改
     */
    public static boolean update(String name, Privilege privilege) {
        String sql = "UPDATE privilege " +
                "SET priName=? " +
                "WHERE priName=?";
        DBAccessUtil.UpdateResult ur =
                DBAccessUtil.updateWrapped(sql,
                        privilege.getName(), name);
        if (ur.exception != null) {
            ur.exception.printStackTrace();
        }

        return ur.state;
    }

    /**
     * 查询
     */
    public static Privilege get(String name) {
        // 查询语句
        String sql = "SELECT * FROM privilege WHERE priName=?";
        // 创建结果集处理器
        ResultSetHandler rsh = (ResultSet rs) -> {
            Privilege privilege = null;
            if (rs.next()) {
                privilege = new Privilege();
                privilege.setName(rs.getString(1));
            }
            return privilege;
        };
        // 执行查询
        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rsh, name);
        // 如果有异常则处理异常
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }
        // 返回查询结果
        return (Privilege) qr.result;
    }

    @SuppressWarnings("unchecked cast")
    public static Iterator<Privilege> getAll() {
        String sql = "SELECT * FROM privilege";
        ResultSetHandler rsh = (ResultSet rs) -> {
            List<Privilege> l = new LinkedList<>();
            Privilege p;
            while (rs.next()) {
                p = new Privilege();
                p.setName(rs.getString(1));
                l.add(p);
            }
            return l;
        };

        DBAccessUtil.QueryResult qr =
                DBAccessUtil.queryWrapped(sql, rsh);
        if (qr.exception != null) {
            qr.exception.printStackTrace();
        }

        return ((List<Privilege>) qr.result).iterator();
    }

    // 测试
    public static void main(String[] args) {
        System.out.println("查询全部：");
        for (Iterator<Privilege> it = getAll(); it.hasNext(); ) {
            Privilege p = it.next();
            System.out.println(p);
        }

        System.out.println("添加+查询全部：");
        add(new Privilege("测试"));
        for (Iterator<Privilege> it = getAll(); it.hasNext(); ) {
            Privilege p = it.next();
            System.out.println(p);
        }

        System.out.println("修改+查询全部");
        update("测试", new Privilege("测试1"));
        for (Iterator<Privilege> it = getAll(); it.hasNext(); ) {
            Privilege p = it.next();
            System.out.println(p);
        }

        System.out.println("删除+查询全部：");
        delete("测试1");
        for (Iterator<Privilege> it = getAll(); it.hasNext(); ) {
            Privilege p = it.next();
            System.out.println(p);
        }
    }
}


