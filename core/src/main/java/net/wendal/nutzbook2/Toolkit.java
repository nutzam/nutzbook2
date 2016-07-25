package net.wendal.nutzbook2;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by wendal on 2016/7/24.
 */
public class Toolkit {

    public static boolean lenBetween(String str, int min, int max) {
        int len = str == null ? 0 : str.length();
        return len >= min && len <= max;
    }

    public static int uid() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            return (Integer) subject.getPrincipal();
        return -1;
    }
}
