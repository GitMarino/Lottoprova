package provalotto.datalayer.manager;

import provalotto.bean.bean.UserBean;

public interface UserManager {

	void createUser(UserBean userBean);

	void setPassword(UserBean userBean);

}