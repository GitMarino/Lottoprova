package provalotto.datalayer.utility;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;

@Mapper(componentModel = "spring")
public interface EntityBeanMapper {

	@Mapping(target = "value", source = "area.name")
	KeyValueBean mapAreaKV(Area area);

	PersonBean mapPerson(Person person);

	Person mapPersonBean(PersonBean personBean);

	@Mapping(target = "value", source = "person.surname")
	KeyValueBean mapPersonKV(Person person);

	@Mapping(target = "value", source = "skill.name")
	KeyValueBean mapSkillKV(Skill skill);

	@Mapping(target = "value", source = "topic.name")
	KeyValueBean mapTopicKV(Topic topic);

}