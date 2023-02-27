package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class PersonAreaConnectionBean implements Serializable {

	private static final long serialVersionUID = -2301617728641316996L;

	private Long personId;

	private Long areaId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonAreaConnectionBean other = (PersonAreaConnectionBean) obj;
		return Objects.equals(areaId, other.areaId) && Objects.equals(personId, other.personId);
	}

	public Long getAreaId() {
		return areaId;
	}

	public Long getPersonId() {
		return personId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaId, personId);
	}

	public void setAreaId(final Long areaId) {
		this.areaId = areaId;
	}

	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "PersonAreaConnectionBean [personId=" + personId + ", areaId=" + areaId + "]";
	}
}