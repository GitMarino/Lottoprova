package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Area;

@Repository
public interface AreaDAO extends JpaRepository<Area, Integer> {

	boolean existsByName(String name);

	List<Area> findAllByOrderByName();

	@Query("SELECT a " + "FROM Area a join PersonAreaConnection pa on a.id=pa.id.area.id "
			+ "join Person p on pa.id.person=p.id " + "WHERE p.id=?1 " + "ORDER BY a.name")
	List<Area> findAreasByPerson(Integer personId);

	@Override
	Optional<Area> findById(Integer id);

}