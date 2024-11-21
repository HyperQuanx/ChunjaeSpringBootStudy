package net.fullstack7.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="tbl_board")
public class Board extends BoardBaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique=true, nullable=false)
  private int idx;

  @Column(length=20, nullable=false)
  private String memberId;

  @Column(length=100, nullable=false)
  private String title;

  @Column(length=2000, nullable=false)
  private String content;

  @Column(length=10, nullable=true)
  private String displayDate;

  public void modify(String memberId, String title, String content, String displayDate) {
    this.memberId = memberId;
    this.title = title;
    this.content = content;
    this.displayDate = displayDate;

    super.setModifyDate(LocalDateTime.now());
  }
}
