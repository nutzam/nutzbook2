package net.wendal.nutzbook2.modeuls;

import net.wendal.nutzbook2.Toolkit;
import net.wendal.nutzbook2.bean.Todo;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.service.IdEntityService;

/**
 * Created by wendal on 2016/7/24.
 */
@IocBean(fields = {"dao"})
@At("/api/todo")
@Ok("json:full")
@AdaptBy(type= WhaleAdaptor.class)
public class TodoModule extends IdEntityService<Todo> {

    @At
    @POST
    public NutMap add(@Param("..")Todo todo) {
        if (!Toolkit.lenBetween(todo.getContent(), 1, 200))
            return new NutMap("ok", false).setv("msg", "todo_error_content_length");
        todo.setUserId(Toolkit.uid());
        dao().insert(todo);
        return new NutMap("ok", true).setv("id", todo.getId());
    }

    @At("/delete/?")
    @DELETE
    public NutMap delete(int id) {
        dao().clear(Todo.class, Cnd.where("id", "=", id).and("userId", "=", Toolkit.uid()));
        return new NutMap("ok", true);
    }

    @At("/update/?")
    @POST
    public NutMap update(int id, String content) {
       dao().update(Todo.class, Chain.make("content", content), Cnd.where("id", "=", id).and("userId", "=", Toolkit.uid()));
        return new NutMap("ok", true);
    }

    @At
    public NutMap list(@Param("..")Pager pager) {
        return new NutMap("ok", true).setv("data", dao().query(Todo.class, Cnd.where("userId", "=", Toolkit.uid()), pager));
    }

}
