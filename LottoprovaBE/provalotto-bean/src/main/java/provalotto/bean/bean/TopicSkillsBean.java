package provalotto.bean.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopicSkillsBean implements Serializable {

	private static final long serialVersionUID = -2035208243061879798L;

	private String topicName;

	private Integer average;

	private List<SkillMarkBean> skillsMarks;

}