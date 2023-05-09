package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.bean.UserBean;
import provalotto.bean.entity.User;
import provalotto.datalayer.dao.UserDAO;
import provalotto.datalayer.exceptions.InconsistentDataException;

@ExtendWith(MockitoExtension.class)
public class UserManagerImplTest {

	@Mock
	private UserDAO userDAO;

	@InjectMocks
	private UserManagerImpl userManager;

	@Test
	public void createUserExceptionTest() {
		UserBean userBean = new UserBean();
		userBean.setUsername("simona.aiello@internet-idee.net");
		userBean.setPassword("123");

		when(userDAO.existsByUsername("simona.aiello@internet-idee.net")).thenReturn(true);

		assertThrows(InconsistentDataException.class, () -> userManager.createUser(userBean));

	}

	@Test
	public void setPasswordExceptionTest() {
		UserBean userBean = new UserBean();
		userBean.setUsername("christian.marino@internet-idee.net");
		userBean.setPassword("123");

		Optional<User> userOptional = Optional.empty();

		when(userDAO.findByUsername(userBean.getUsername())).thenReturn(userOptional);

		assertThrows(InconsistentDataException.class, () -> userManager.setPassword(userBean));

	}

}
