package net.fullstack7.springboot.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class ConditionResponseDTO<E> {
  private int page_no;
  private int page_size;
  private int total_count;
  private int total_page;
  private int page_block_start;
  private int page_block_end;
  private boolean prev_page_flag;
  private boolean next_page_flag;

  private List<E> dtoList;

  private String linkURL;
  private String queryString;

  ConditionResponseDTO() {}

  @Builder(builderMethodName = "withAll")
  ConditionResponseDTO(
      ConditionRequestDTO conditionRequestDTO,
      int total_count,
      List<E> dtoList
  ) {
    this.page_no = conditionRequestDTO.getPage_no();
    this.page_size = conditionRequestDTO.getPage_size();
    this.total_count = total_count;
    this.total_page = (int)Math.ceil(total_count / (double)this.page_size);
    this.page_block_end = (int)(Math.ceil(this.page_no / (double)conditionRequestDTO.getPage_block_size())) * conditionRequestDTO.getPage_block_size();
    this.page_block_end = Math.min(this.page_block_end, this.total_page);
    this.page_block_start = Math.max(1, this.page_block_end - (conditionRequestDTO.getPage_block_size() - 1));
    this.prev_page_flag = this.page_block_start > 1;
    this.next_page_flag = this.total_page > this.page_block_end;
    this.dtoList = dtoList;

    StringBuilder sb = new StringBuilder();
    sb.append("page_no=" + this.page_no);
    sb.append("&page_size=" + this.page_size);
    if (conditionRequestDTO.getSearch_type() != null && conditionRequestDTO.getSearch_word() != null) {
      sb.append("&search_type=" + conditionRequestDTO.getSearch_type());
      sb.append("&search_word=" + conditionRequestDTO.getSearch_word());
    }
    this.queryString = sb.toString();
  }

  // 이전 페이지 블록의 시작 페이지
  public int getPrev_page() {
    return page_block_start > 1 ? page_block_start - 10 : 0;
  }

  // 다음 페이지 블록의 시작 페이지
  public int getNext_page() {
    int total_pages = (int) Math.ceil((double) total_count / page_size);
    return page_block_end < total_pages ? page_block_end + 1 : 0;
  }
}
