package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class AreaBean implements Serializable {

	private static final long serialVersionUID = 7540913196740748013L;

	private Long id;

	private String name;

	private PersonBean manager;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaBean other = (AreaBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(manager, other.manager)
				&& Objects.equals(name, other.name);
	}

	public Long getId() {
		return id;
	}

	public PersonBean getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, manager, name);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setManager(final PersonBean manager) {
		this.manager = manager;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AreaBean [id=" + id + ", name=" + name + ", manager=" + manager + "]";
	}

}