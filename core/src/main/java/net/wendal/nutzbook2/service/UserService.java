package net.wendal.nutzbook2.service;

import net.wendal.nutzbook2.bean.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.service.IdNameEntityService;

import java.util.Date;

@IocBean(fields = "dao")
public class UserService extends IdNameEntityService<User> {

    public User add(String name, String password) {
        User user = new User();
        user.setName(name.trim().toLowerCase());
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setNickname(user.getName());
        user = dao().insert(user);
        return user;
    }

    public int fetch(String username, String password) {
        User user = fetch(username);
        if (user == null) {
            return -1;
        }
        String _pass = new Sha256Hash(password, user.getSalt()).toHex();
        if (_pass.equalsIgnoreCase(user.getPassword())) {
            return user.getId();
        }
        return -1;
    }

    public void updatePassword(int userId, String password) {
        User user = fetch(userId);
        if (user == null) {
            return;
        }
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        user.setUpdateTime(new Date());
        dao().update(user, "^(password|salt|updateTime)$");
    }

    public boolean checkPassword(User user, String password) {
        String face = new Sha256Hash(password, user.getSalt()).toHex();
        return face.equalsIgnoreCase(user.getPassword());
    }

    public User fetch(int id) {
        User user = dao().fetch(getEntityClass(), Cnd.where("id", "=", id));
        if (!Lang.isEmpty(user)) {
            dao().fetchLinks(user, "roles");
        }
        return user;
    }
}
