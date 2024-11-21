package net.fullstack7.springboot.service;

import net.fullstack7.springboot.domain.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;

public interface BoardServiceIf {
  public int regist(BoardDTO dto);
  public BoardDTO view(int idx);
  public int modify(BoardDTO dto);
  public void delete(int idx);
  public ConditionResponseDTO<BoardDTO> list(ConditionRequestDTO conditionRequestDTO);
}
