package net.fullstack7.springboot.repository;

import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardSearch {
  @Query(value="SELECT NOW()", nativeQuery = true)
  public Long getNow();

  Page<Board> search1(Pageable pageable);
}
