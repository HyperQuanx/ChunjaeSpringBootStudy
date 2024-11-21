package net.fullstack7.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
  @Builder.Default
  @Positive
  @Min(value = 1)
  private int page_no = 1;

  @Builder.Default
  @Positive
  @Min(value = 1)
  private int page_size = 1;

  @Builder.Default
  @PositiveOrZero
  @Min(value = 0)
  private int page_skip_count = 0;

  @Builder.Default
  @Positive
  @Min(value = 1)
  private int page_block_size = 10;

  private String search_type;
  private String search_word;
  private String search_date1;
  private String search_date2;

  public int getPage_skip_count() {
    return (this.page_no - 1) * this.page_size;
  }
}
