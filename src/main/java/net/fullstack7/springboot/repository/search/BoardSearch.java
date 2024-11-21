package net.fullstack7.springboot.repository.search;

import net.fullstack7.springboot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
  Page<Board> search1(Pageable pageable);
  Page<Board> search2(Pageable pageable, String[] searchCategory, String search);
}
