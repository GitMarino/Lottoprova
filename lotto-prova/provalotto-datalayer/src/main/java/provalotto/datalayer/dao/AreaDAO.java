package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Area;

@Repository
public interface AreaDAO extends JpaRepository<Area, Long> {

	List<Area> findAllByOrderByName();

}