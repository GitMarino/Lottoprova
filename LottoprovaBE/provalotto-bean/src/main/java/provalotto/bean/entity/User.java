package provalotto.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.base.AbstractIdentifiedBean;

@Getter
@Setter
@ToString
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractIdentifiedBean {

	public static final String TABLE_NAME = "system_user";

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(nullable = true, length = 60)
	private String password;

}