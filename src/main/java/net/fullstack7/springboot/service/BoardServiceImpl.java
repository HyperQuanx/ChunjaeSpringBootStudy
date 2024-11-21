package net.fullstack7.springboot.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.domain.Board;
import net.fullstack7.springboot.domain.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import net.fullstack7.springboot.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardServiceIf {
  private final ModelMapper modelMapper;
  private final BoardRepository boardRepository;
  @Override
  public int regist(BoardDTO boardDTO) {
    Board board = modelMapper.map(boardDTO, Board.class);
    return boardRepository.save(board).getIdx();
  }

  @Override
  public BoardDTO view(int idx) {
    Optional<Board> result = boardRepository.findById(idx);
    Board board = result.orElse(null);
    BoardDTO dto = modelMapper.map(board, BoardDTO.class);
    return dto;
  }

  @Override
  public int modify(BoardDTO dto) {
    // Board board = modelMapper.map(dto, Board.class);
    Optional<Board> result = boardRepository.findById(dto.getIdx());
    Board board = result.orElse(null);
    board.modify(dto.getMemberId(), dto.getTitle(), dto.getContent(), dto.getDisplayDate());
    return boardRepository.save(board).getIdx();
  }

  @Override
  public void delete(int idx) {
    boardRepository.deleteById(idx);
  }

  @Override
  public ConditionResponseDTO<BoardDTO> list(ConditionRequestDTO conditionRequestDTO) {
    String[] search_type = conditionRequestDTO.getSearch_type();
    String search_word = conditionRequestDTO.getSearch_word();
    Pageable pageable = conditionRequestDTO.getPageable("idx");
    Page<Board> result = boardRepository.search2(pageable, search_type, search_word);
    List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();

    return ConditionResponseDTO.<BoardDTO>withAll().conditionRequestDTO(conditionRequestDTO).total_count((int)result.getTotalElements()).dtoList(dtoList).build();
  }
}
