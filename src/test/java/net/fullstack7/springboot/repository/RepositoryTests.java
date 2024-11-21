package net.fullstack7.springboot.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class RepositoryTests {

  @Autowired
  private BoardRepository boardRepository;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Test
  public void testRegist() {
    log.info("==============================");
    log.info("RepositoryTests >> Regist Board");

    IntStream.rangeClosed(111, 1515).forEach(i -> {
      LocalDate displayDate = LocalDate.of(2024, 11, (i % 10 == 0 ? 1 : i % 10));

      Board board = Board.builder()
          .memberId("user1")
          .title("테스트 제목 " + i)
          .content("테스트 내용 " + i)
          .displayDate(displayDate.format(FORMATTER))
          .build();

      Board savedBoard = boardRepository.save(board);
      log.info("savedBoard : {}", savedBoard);
      log.info("idx : {}", savedBoard.getIdx());
    });

    log.info("RepositoryTests >> Regist Board");
    log.info("==============================");
  }

  @Test
  public void testView() {
    int idx = 1;
    boardRepository.findById(idx).ifPresentOrElse(
        board -> {
          log.info("==========================================================================================");
          log.info("idx : {}", idx);
          log.info("board : {}", board);
          log.info("==========================================================================================");
        },
        () -> log.warn("Board with idx {} not found", idx)
    );
  }

  @Test
  public void testModify() {
    int idx = 2;
    Optional<Board> result = boardRepository.findById(idx);

    result.ifPresentOrElse(board -> {
          LocalDate displayDate = LocalDate.of(2024, 11, (idx % 10 == 0 ? 1 : idx % 10));
          Board updatedBoard = Board.builder()
              .memberId("user1")
              .title("테스트 제목 " + idx + "수정")
              .content("테스트 내용 " + idx + "수정")
              .displayDate(displayDate.format(FORMATTER))
              .build();

          board.modify(updatedBoard.getMemberId(), updatedBoard.getTitle(), updatedBoard.getContent(), updatedBoard.getDisplayDate());
          boardRepository.save(board);

          log.info("==========================================================================================");
          log.info("Updated board : {}", board);
          log.info("==========================================================================================");
        },
        () -> log.warn("Board with idx {} not found", idx));
  }

  @Test
  public void testDelete() {
    int idx = 3;
    boardRepository.findById(idx).ifPresentOrElse(
        board -> {
          boardRepository.delete(board);
          log.info("==========================================================================================");
          log.info("Deleted board with idx : {}", idx);
          log.info("==========================================================================================");
        },
        () -> log.warn("Board with idx {} not found", idx)
    );
  }

//  @Test
//  public void testSelect() {
//    PageRequest pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
//    Page<Board> result = boardRepository.findAll(pageable);
//    List<Board> boards = result.getContent();
//    log.info("==========================================================================================");
//    log.info("Find board with result : {}", result);
//    log.info("Find board with boards : {}", boards);
//    log.info("==========================================================================================");
//  }


//  @Test
//  public void testSearch1() {
//    PageRequest pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
//    boardRepository.search1(pageable);
//
//    log.info("==========================================================================================");
//    log.info("pageable : {}", pageable);
//    log.info("==========================================================================================");
//  }

  @Test
  public void testSearch2() {
    String[] searchCategory = {"title", "content", "memberId"};
    String searchWord = "1";
    PageRequest pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
    Page<Board> boardList = boardRepository.search2(pageable, searchCategory, searchWord);

    log.info("==========================================================================================");
    log.info("total page : {}", boardList.getTotalPages());
    log.info("total size : {}", boardList.getSize());
    log.info("page number : {}", boardList.getNumber());
    log.info("prev : {}", boardList.hasPrevious());
    log.info("next : {}", boardList.hasNext());
    boardList.getContent().forEach(board -> {
      log.info(board.toString());
    });
    log.info("==========================================================================================");
  }
}
