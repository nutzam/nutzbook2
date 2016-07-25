package net.wendal.nutzbook2.bean;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

@Table("t_permission")
public class Permission extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	protected long id;
	@Name
	protected String name;
	@Column("al")
	protected String alias;
	@Column("dt")
	@ColDefine(type = ColType.VARCHAR, width = 500)
	private String description;

	@Column("permission_category_id")
	private String permissionCategoryId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
