package net.wendal.nutzbook2.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by wendal on 2016/7/24.
 */
@Table("t_todo")
public class Todo extends BasePojo {
    @Id
    protected int id;

    @Column("u_id")
    protected int userId;

    @Column("cpt")
    protected Date completeTime;
    @Column("cnt")
    @ColDefine(width = 1024)
    protected String content;

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
