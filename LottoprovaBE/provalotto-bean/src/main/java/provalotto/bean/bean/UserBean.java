package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBean implements Serializable {

	private static final long serialVersionUID = -6132601051040168461L;

	private String username;

	private String password;

}