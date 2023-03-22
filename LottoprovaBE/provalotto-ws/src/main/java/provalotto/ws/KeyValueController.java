package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.manager.KeyValueManager;
import provalotto.ws.response.Answer;

@RestController
@RequestMapping("/keyValue")
public class KeyValueController {

	@Autowired
	private KeyValueManager keyValueManager;

	@GetMapping
	public Answer<List<List<KeyValueBean>>> getAreaTopicSkillMap() {
		return Answer.ok(keyValueManager.getAreaTopicSkillMap(), HttpStatus.OK);
	}

}