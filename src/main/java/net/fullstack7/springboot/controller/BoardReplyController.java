package net.fullstack7.springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.dto.BoardReplyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("board/reply")
public class BoardReplyController {
  @Tag(name="REGIST", description = "POST 방식 댓글 등록")
  @PostMapping(value="/regist", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Integer>> regist (
      @RequestBody BoardReplyDTO replyDTO
  ) {
      Map<String,Integer> map = Map.of("idx", 1);
      return ResponseEntity.ok(map);
  }
}
