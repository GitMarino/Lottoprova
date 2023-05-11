package provalotto.bean.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Topic;

@Getter
@Setter
@ToString
@Embeddable
public class AreaTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = 6171640641676123037L;

	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	Area area;

	@ManyToOne
	@JoinColumn(name = "topic_id", nullable = false)
	Topic topic;

}