package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonAreaConnectionKey implements Serializable {

	private static final long serialVersionUID = -6297954423935509232L;

	@Column(name = "person_id")
	Long personId;

	@Column(name = "area_id")
	Long areaId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonAreaConnectionKey other = (PersonAreaConnectionKey) obj;
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
		return "PersonAreaConnectionKey [personId=" + personId + ", areaId=" + areaId + "]";
	}

}
