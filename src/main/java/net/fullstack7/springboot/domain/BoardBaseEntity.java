package net.fullstack7.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter

public abstract class BoardBaseEntity {
  @CreatedDate
  @Column(name="regDate", updatable = false)
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name="modifyDate", nullable = true, insertable = false, updatable = true)
  private LocalDateTime modifyDate;

  public void setModifyDate() {
    this.modifyDate = LocalDateTime.now();
  }

  public void setModifyDate(LocalDateTime modifyDate) {
    this.modifyDate = LocalDateTime.now();
  }
}
