package net.wendal.nutzbook2.modeuls;

import net.wendal.nutzbook2.Toolkit;
import net.wendal.nutzbook2.bean.User;
import net.wendal.nutzbook2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Dao;
import org.nutz.integration.shiro.NutShiro;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by wendal on 2016/7/22.
 */
@At("/api/user")
@IocBean
@Ok("json:full")
public class UserModule {

    private static final boolean DEV_MODE = true;

    @Inject
    Dao dao;

    @Inject
    UserService userService;

    @At
    @POST
    @AdaptBy(type= WhaleAdaptor.class)
    public Object login(String username, String password, String captcha,
                        @Attr("captcha")String _captcha, HttpSession session) throws Exception {
        NutMap re = new NutMap();
        if (!DEV_MODE && (_captcha == null || !_captcha.equalsIgnoreCase(captcha))) {
            return re.setv("ok", false).setv("msg", "user_login_captcha_error");
        }
        if (!Toolkit.lenBetween(username, 3, 20) || !Toolkit.lenBetween(password, 1, 64)) {
            return re.setv("ok", false).setv("msg", "user_login_username_password_len_error");
        }
        User user = dao.fetch(User.class, username);
        if (user == null) {
            return re.setv("ok", false).setv("msg", "user_login_no_user");
        }
        if (!userService.checkPassword(user, password)) {
            return re.setv("ok", false).setv("msg", "user_login_password_error");
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(new SimpleShiroToken(user.getId()));
        subject.getSession().setAttribute(NutShiro.SessionKey, user.getId());
        return re.setv("ok", true);
    }

    @At
    @POST
    public NutMap logout(HttpSession session) {
        session.invalidate();
        return new NutMap("ok", true);
    }

    @At
    @Ok("json:{locked:'password|salt'}")
    public Object me() {
        int uid = Toolkit.uid();
        if (uid < 0)
            return null;
        return dao.fetch(User.class, uid);
    }

    @At
    @POST
    @AdaptBy(type= WhaleAdaptor.class)
    public NutMap register(String username, String password, String captcha,
                           @Attr("captcha")String _captcha, HttpSession session) {
        NutMap re = new NutMap();
        if (!DEV_MODE && (_captcha == null || !_captcha.equalsIgnoreCase(captcha))) {
            return re.setv("ok", false).setv("msg", "user_register_captcha_error");
        }
        username = username == null ? "" : username.trim();
        password = password == null ? "" : password.trim();
        if (!Toolkit.lenBetween(username, 3, 20) || !Toolkit.lenBetween(password, 1, 64)) {
            return re.setv("ok", false).setv("msg", "user_register_username_password_len_error");
        }
        User user = dao.fetch(User.class, username);
        if (user != null) {
            return re.setv("ok", false).setv("msg", "user_register_username_exist");
        }
        userService.add(username, password);
        return re.setv("ok", true);
    }
}
