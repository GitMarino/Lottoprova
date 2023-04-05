package provalotto.bean.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TopicSkillsBean implements Serializable {

	private static final long serialVersionUID = -2035208243061879798L;

	private String topicName;

	private Integer average;

	private List<SkillMarkBean> skillsMarks;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicSkillsBean other = (TopicSkillsBean) obj;
		return Objects.equals(average, other.average) && Objects.equals(skillsMarks, other.skillsMarks)
				&& Objects.equals(topicName, other.topicName);
	}

	public Integer getAverage() {
		return average;
	}

	public List<SkillMarkBean> getSkillsMarks() {
		return skillsMarks;
	}

	public String getTopicName() {
		return topicName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(average, skillsMarks, topicName);
	}

	public void setAverage(final Integer average) {
		this.average = average;
	}

	public void setSkillsMarks(final List<SkillMarkBean> skillsMarks) {
		this.skillsMarks = skillsMarks;
	}

	public void setTopicName(final String topicName) {
		this.topicName = topicName;
	}

	@Override
	public String toString() {
		return "TopicSkillsBean [topicName=" + topicName + ", average=" + average + ", skillsMarks=" + skillsMarks
				+ "]";
	}

}