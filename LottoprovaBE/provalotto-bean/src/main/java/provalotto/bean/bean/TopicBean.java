package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class TopicBean implements Serializable {

	private static final long serialVersionUID = 2686995141118232899L;

	private Long id;

	private String name;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicBean other = (TopicBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TopicBean [id=" + id + ", name=" + name + "]";
	}

}