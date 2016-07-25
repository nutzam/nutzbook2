package net.wendal.nutzbook2;

import net.wendal.nutzbook2.bean.User;
import net.wendal.nutzbook2.service.UserService;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.quartz.Scheduler;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Created by wendal on 2016/7/25.
 */
public class MainSetup implements Setup {


    private static final Log log = Logs.get().setTag("r.main");

    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);

        Daos.createTablesInPackage(dao, getClass().getPackage().getName(), false);
        Daos.migration(dao, User.class, true, false, false);

        User systemUser = dao.fetch(User.class, "system");
        if (systemUser == null) {
            ioc.get(UserService.class).add("system", "1");
        }
    }

    public void destroy(NutConfig nc) {
        // 非mysql数据库,或多webapp共享mysql驱动的话,以下语句删掉
        try {
            Mirror.me(Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread")).invoke(null, "shutdown");
        } catch (Throwable e) {
        }
        // 解决quartz有时候无法停止的问题
        try {
            nc.getIoc().get(Scheduler.class).shutdown(true);
        } catch (Exception e) {
        }
        // 解决com.alibaba.druid.proxy.DruidDriver和com.mysql.jdbc.Driver在reload时报warning的问题
        // 多webapp共享mysql驱动的话,以下语句删掉
        Enumeration<Driver> en = DriverManager.getDrivers();
        while (en.hasMoreElements()) {
            try {
                Driver driver = en.nextElement();
                String className = driver.getClass().getName();
                if ("com.alibaba.druid.proxy.DruidDriver".equals(className)
                        || "com.mysql.jdbc.Driver".equals(className)) {
                    log.debug("deregisterDriver: " + className);
                    DriverManager.deregisterDriver(driver);
                }
            } catch (Exception e) {
            }
        }
    }
}
