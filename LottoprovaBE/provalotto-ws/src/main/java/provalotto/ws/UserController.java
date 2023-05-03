package provalotto.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.UserBean;
import provalotto.datalayer.manager.UserManager;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;

	@PostMapping
	public ResponseEntity<Integer> createUser(final @RequestBody UserBean userBean) {
		try {
			userManager.createUser(userBean);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();

		}
	}

	@PutMapping
	public ResponseEntity<Integer> setPassword(final @RequestBody UserBean userBean) {
		try {
			userManager.setPassword(userBean);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}