package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SkillMarkBean implements Serializable {

	private static final long serialVersionUID = 2378686551905078922L;

	private String skillName;

	private Integer mark;

}