package net.fullstack7.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Table(name = "tbl_board_reply", indexes = {
  @Index(
    name = "IDX_tbl_board_reply_refIdx",
    columnList = "bbs_idx"
  )
})
public class BoardReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idx; // 게시글 고유 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_idx")
    private Board board;

    @Column(nullable = false)
    private int bbsIdx; // 참조 게시글 번호

    private int levelIdx; // 답글 레벨

    @Column(length = 20, nullable = false)
    private String replyMemberId; // 답글 작성자 ID

    @Column(length = 100, nullable = false)
    private String replyTitle; // 답글 제목

    @Column(length = 1000, nullable = false)
    private String replyContent; // 답글 내용

    @Column(nullable = false, columnDefinition = "DATETIME NOT NULL DEFAULT now()")
    private LocalDateTime replyRegDate; // 답글 등록일

    @Column(nullable = true)
    private LocalDateTime replyModifyDate; // 답글 수정일
}
