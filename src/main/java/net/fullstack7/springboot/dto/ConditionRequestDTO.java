package net.fullstack7.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Log4j2
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionRequestDTO {
  @Builder.Default
  @Positive
  @Min(1)
  private int page_no = 1;

  @Builder.Default
  @Positive
  @Min(1)
  private int page_size = 10;

  @Builder.Default
  @Positive
  @Min(1)
  private int page_block_size = 10;


  private String search_type;
  private String search_word;

  private String returnLink;

  public String[] getSearch_type() {

    return (search_type != null && !search_type.isEmpty()) ? search_type.split("") : null;
  }

  public Pageable getPageable(String...params) {
    return PageRequest.of(this.page_no - 1, this.page_size, Sort.by(params).descending());
  }

  public String getReturnLink() {
    if (returnLink == null || returnLink.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      sb.append("page_no=" + this.page_no);
      sb.append("&page_size=" + this.page_size);

      if(search_type != null && search_word != null) {
        try {
          sb.append("&search_type=" + this.search_type);
          sb.append("&search_word=" + URLEncoder.encode(search_word, "UTF-8")); // 참조해서 넣어봄
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      }
    }

    return returnLink;
  }
}
