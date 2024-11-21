package net.fullstack7.springboot.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import java.util.Locale.Category;
import java.util.Objects;
import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

  public BoardSearchImpl() {
    super(Board.class);
  }

  @Override
  public Page<Board> search1(Pageable pageable) {
    QBoard boardQ = QBoard.board;
    JPQLQuery<Board> query = from(boardQ); // SELECT ~~ FROM tbl_board
//    query.where(boardQ.title.like("1")); // WHERE title like '%1%'

    BooleanBuilder bb = new BooleanBuilder();
    bb.or(boardQ.title.like("1"));
    bb.or(boardQ.title.like("2"));
    query.where(bb);

    // 페이징 추가
    Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);

    List<Board> result = query.fetch();
    int total = (int) query.fetchCount();
    return null;
  }

  @Override
  public Page<Board> search2(Pageable pageable, String[] searchCategory, String search_word) {
    QBoard boardQ = QBoard.board;
    JPQLQuery<Board> query = from(boardQ); // SELECT ~~ FROM tbl_board

    if ((searchCategory != null) && (searchCategory.length > 0) && search_word != null) {
      BooleanBuilder bb = new BooleanBuilder();

      for(String category : searchCategory) {
        switch (category) {
          case "t":
            bb.or(boardQ.title.like("%" + search_word + "%"));
//            bb.or(boardQ.title.containsIgnoreCase("%" + category + "%"));
            break;
          case "c":
            bb.or(boardQ.content.like("%" + search_word + "%"));
            break;
          case "m":
            bb.or(boardQ.memberId.like("%" + search_word + "%"));
            break;
        }
      }
      query.where(bb);
      query.where(boardQ.idx.gt(0));
    }

    // 페이징 추가
    Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
    List<Board> result = query.fetch();
    int total = (int) query.fetchCount();
    return new PageImpl<Board>(result, pageable, total);
  }
}
