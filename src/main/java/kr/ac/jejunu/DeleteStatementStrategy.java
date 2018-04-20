package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatmentStrategy {
    private Long id;

    public DeleteStatementStrategy(Long id) {
        this.id=id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id=?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
