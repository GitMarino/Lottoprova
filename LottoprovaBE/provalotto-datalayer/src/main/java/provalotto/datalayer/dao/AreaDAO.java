package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Area;

@Repository
public interface AreaDAO extends JpaRepository<Area, Long> {

	boolean existsByName(String name);

	List<Area> findAllByOrderByName();

	@Override
	Optional<Area> findById(Long id);

}