package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -6132601051040168461L;

	private String username;

	private String password;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
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
		return Objects.hash(password, username);
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + "]";
	}
}