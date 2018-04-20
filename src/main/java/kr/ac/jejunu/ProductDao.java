package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext = new JdbcContext();

    public ProductDao(DataSource dataSource) {
        this.jdbcContext.dataSource = dataSource;
    }
    public Product get(Long id) throws SQLException {
        StatmentStrategy statmentStrategy=connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statmentStrategy);
    }

    public Long insert(Product product) throws SQLException {
        StatmentStrategy statmentStrategy= connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price) values(?, ? )", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForInsert(statmentStrategy);
    }

    public void update(Product product) throws SQLException {
        Product product1 = product;
        StatmentStrategy statmentStrategy= new StatmentStrategy() {
            private Product product = product1;

            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("update product set title=?, price=? where id=?");
                preparedStatement.setString(1, product.getTitle());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.setLong(3, product.getId());
                return preparedStatement;
            }
        };
        jdbcContext.jdbcContextForUpdate(statmentStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatmentStrategy statmentStrategy= connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id=?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statmentStrategy);
    }
}
