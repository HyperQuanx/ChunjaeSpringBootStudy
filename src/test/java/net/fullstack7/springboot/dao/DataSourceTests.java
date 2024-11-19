package net.fullstack7.springboot.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class DataSourceTests {
  @Autowired
  private DataSource dataSource;

//  @Test
//  public void testDataSource() throws SQLException {
//    @Cleanup Connection con = dataSource.getConnection();
//    Assertions.assertNotNull(con);
//    log.info(con);
//  }
}
