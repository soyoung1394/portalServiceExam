package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext = new JdbcContext();

    public ProductDao(DataSource dataSource) {
        this.jdbcContext.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
        StatmentStrategy statmentStrategy=new GetStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statmentStrategy);
    }
    public Long insert(Product product) throws SQLException {
        StatmentStrategy statmentStrategy=new InsertStatementStrategy(product);
        return jdbcContext.jdbcContextForInsert(statmentStrategy);
    }
    public void update(Product product) throws SQLException {
        StatmentStrategy statmentStrategy=new UpdateStatementStrategy(product);
        jdbcContext.jdbcContextForUpdate(statmentStrategy);
    }
    public void delete(Long id) throws SQLException {
        StatmentStrategy statmentStrategy=new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statmentStrategy);
    }
}
