package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class AreaBean implements Serializable {

	private static final long serialVersionUID = 7540913196740748013L;

	private Long id;

	private String name;

	private String areaManager;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaBean other = (AreaBean) obj;
		return Objects.equals(areaManager, other.areaManager) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	public String getAreaManager() {
		return areaManager;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaManager, id, name);
	}

	public void setAreaManager(final String areaManager) {
		this.areaManager = areaManager;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AreaBean [id=" + id + ", name=" + name + ", areaManager=" + areaManager + "]";
	}

}