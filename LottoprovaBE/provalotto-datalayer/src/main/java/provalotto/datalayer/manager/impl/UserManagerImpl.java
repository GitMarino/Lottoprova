package provalotto.datalayer.manager.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.UserBean;
import provalotto.bean.entity.User;
import provalotto.datalayer.dao.UserDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.InconsistentDataException;
import provalotto.datalayer.manager.UserManager;

@Component
public class UserManagerImpl implements UserManager {

	public static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void createUser(final UserBean userBean) {
		try {
			String username = userBean.getUsername();
			if (!userDAO.existsByUsername(username)) {
				User user = new User();
				user.setUsername(username);
				userDAO.save(user);
			} else {
				throw new InconsistentDataException();
			}
		} catch (InconsistentDataException ide) {
			log.error(ide.getMessage(), ide);
			throw ide;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	@Transactional
	public void setPassword(final UserBean userBean) {
		try {
			String username = userBean.getUsername();
			Optional<User> userOptional = userDAO.findByUsername(username);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				user.setPassword(passwordEncoder.encode(userBean.getPassword()));
				userDAO.save(user);
			} else {
				throw new InconsistentDataException();
			}
		} catch (InconsistentDataException ide) {
			log.error(ide.getMessage(), ide);
			throw ide;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

}