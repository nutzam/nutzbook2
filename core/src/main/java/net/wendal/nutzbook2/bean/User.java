package net.wendal.nutzbook2.bean;

import org.nutz.dao.entity.annotation.*;
import org.nutz.json.JsonField;

import java.io.Serializable;
import java.util.List;

@Table("t_user")
public class User extends BasePojo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	protected int id;
	@Name
	@Column
	protected String name;
	@Column("passwd")
	@ColDefine(width=128)
	protected String password;
	@Column
	protected String salt;
	@Column
	protected boolean locked;
	@ManyMany(from="u_id", relation="t_user_role", target=Role.class, to="role_id")
	protected List<Role> roles;
	@ManyMany(from="u_id", relation="t_user_permission", target=Permission.class, to="permission_id")
	protected List<Permission> permissions;

	/**用户昵称*/
	@Column
	protected String nickname;
	/**用户邮箱*/
	@Column
	protected String email;
	/**邮箱是否已经验证过*/
	@Column("email_checked")
	protected boolean emailChecked;
	/**头像的byte数据*/
	@Column
	@JsonField(ignore=true)
	protected byte[] avatar;
	/**性别*/
	@Column
	protected String gender;
	/**自我介绍*/
	@Column("dt")
	protected String description;
	@Column("loc")
	protected String location;

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailChecked() {
		return emailChecked;
	}
	public void setEmailChecked(boolean emailChecked) {
		this.emailChecked = emailChecked;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
