package com.dipsec.demo.repositories;

import com.dipsec.demo.model.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "select * from position where name ilike '%' || :name || '%'", nativeQuery = true)
    List<Position> findByNameLike(@Param("name") String name);
}
