package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.config.TestConfig;
import md.dbalutsel.BookStore.config.TestDataConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class})
public class BookDaoTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    BookDao bookDao;

    @Test
    public void Test() {

    }
}
