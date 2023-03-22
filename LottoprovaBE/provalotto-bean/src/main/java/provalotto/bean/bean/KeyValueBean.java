package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class KeyValueBean implements Serializable {

	private static final long serialVersionUID = -5797384763938225572L;

	private Long id;

	private String value;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyValueBean other = (KeyValueBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(value, other.value);
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BeanKeyValue [id=" + id + ", value=" + value + "]";
	}

}