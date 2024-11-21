package net.fullstack7.springboot.service;

import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private BoardServiceIf boardService;

  @Test
  public void testRegist() {
    log.info("=======================================================");

    IntStream.rangeClosed(32, 10).forEach(i -> {
      Board board = Board.builder()
          .memberId("user2")
          .title("제목 " + i)
          .content("내용 " + i)
          .displayDate("2024-11-20")
          .build();
      int rtnResult = boardService.regist(modelMapper.map(board, BoardDTO.class));
      log.info("rtnResult : " + rtnResult);
    });
    log.info("=======================================================");
  }

  @Test
  public void testView() {
    int idx = 35;
    BoardDTO dto = boardService.view(idx);
    log.info("=======================================================");
    log.info("dto : " + dto);
    log.info("=======================================================");
  }

  @Test
  public void testModify() {
    int idx = 35;
    Board board = Board.builder()
        .idx(idx)
        .memberId("user2")
        .title("수정되었니 " + idx + "야")
        .content("content" + idx)
        .displayDate("2024-11-20")
        .build();
    int rtnResult = boardService.modify(modelMapper.map(board, BoardDTO.class));
    log.info("=======================================================");
    log.info("수정 결과 : " + rtnResult);
    log.info("=======================================================");
  }

  @Test
  public void testDelete() {
    int idx = 36;
    boardService.delete(idx);
    log.info("=======================================================");
    log.info("삭제 결과 : " + idx);
    log.info("=======================================================");
  }

  @Test
  public void testList() {
    ConditionRequestDTO requestDTO = ConditionRequestDTO.builder()
        .page_no(1)
        .page_size(10)
        .page_block_size(10)
        .build();
    ConditionResponseDTO<BoardDTO> responseDTO = boardService.list(requestDTO);
    log.info("conditionDTO : " + requestDTO);
    log.info("conditionDTO : " + responseDTO);
  }
}
