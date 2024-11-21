package net.fullstack7.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import net.fullstack7.springboot.service.BoardServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardServiceIf boardService;

  @GetMapping("/list")
  public String list(
    ConditionRequestDTO requestDTO,
    Model model
  ) {
    ConditionResponseDTO responseDTO = boardService.list(requestDTO);
    model.addAttribute("requestDTO", requestDTO);
    model.addAttribute("responseDTO", responseDTO);
    return "board/list";
  }

// 새로 추가하는 비동기 검색용 엔드포인트
  @GetMapping("/search")
  @ResponseBody
  public ConditionResponseDTO search(ConditionRequestDTO requestDTO) {
      log.info("search request: " + requestDTO);
      return boardService.list(requestDTO);
  }
  
  // 등록 페이지 이동
  @GetMapping("/regist")
  public String registForm() {
      return "board/regist";
  }

  // 등록 처리
  @PostMapping("/regist")
  public String regist(BoardDTO boardDTO) {
      int idx = boardService.regist(boardDTO);
      return "redirect:/board/list";
  }

  // 조회
  @GetMapping("/view")
  public String view(int idx, Model model) {
      BoardDTO boardDTO = boardService.view(idx);
      model.addAttribute("dto", boardDTO);
      return "board/view";
  }

  // 수정 페이지 이동
  @GetMapping("/modify")
  public String modifyForm(int idx, Model model) {
      BoardDTO boardDTO = boardService.view(idx);
      model.addAttribute("dto", boardDTO);
      return "board/modify";
  }

  // 수정 처리
  @PostMapping("/modify")
  public String modify(BoardDTO boardDTO) {
      boardService.modify(boardDTO);
      return "redirect:/board/view?idx=" + boardDTO.getIdx();
  }

  // 삭제
  @PostMapping("/delete")
  public String delete(int idx) {
      boardService.delete(idx);
      return "redirect:/board/list";
  }
}
