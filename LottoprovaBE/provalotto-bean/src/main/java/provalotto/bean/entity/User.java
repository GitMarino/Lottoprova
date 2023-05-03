package provalotto.bean.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import provalotto.bean.base.AbstractIdentifiedBean;

@Entity(name = User.TABLE_NAME)
public class User extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 8132049981567636363L;

	public static final String TABLE_NAME = "system_user";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(nullable = true, length = 60)
	private String password;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, username);
		return result;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

}