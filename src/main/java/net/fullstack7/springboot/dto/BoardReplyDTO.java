package net.fullstack7.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyDTO {
  @PositiveOrZero
  @Min(1)
  private int idx; // 게시글 고유 번호

  @Builder.Default
  @PositiveOrZero
  @Min(0)
  private int bbsIdx = 0; // 참조 게시글 번호

  @Builder.Default
  @PositiveOrZero
  @Min(0)
  private int levelIdx = 0; // 답글 레벨

  @NotNull
  private String replyMemberId; // 답글 작성자 ID

  @NotNull
  @Size(min = 2, max = 100)
  private String replyTitle; // 답글 제목

  @NotNull
  @Size(min = 2, max = 1000)
  private String replyContent; // 답글 내용

  @JsonFormat(shape = JsonFormat.Shape.STRING,
  pattern = "yyyy-MM-dd HH:mm:ss",
  timezone = "Asia/Seoul")
  private LocalDateTime replyRegDate; // 답글 등록일
  
  @JsonFormat(shape = JsonFormat.Shape.STRING,
  pattern = "yyyy-MM-dd HH:mm:ss",
  timezone = "Asia/Seoul")
  private LocalDateTime replyModifyDate; // 답글 수정일
}
