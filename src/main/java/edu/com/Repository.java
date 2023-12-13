package edu.com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Repository {
    private static Repository INSTANCE;
    private static final String url = "jdbc:mysql://localhost/equations";
    private static final String username = "root";
    private static final String password = "100304";
    private Connection connection;
    Repository() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }



    public void save(String equation) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO equations (equation) VALUES (?)");
        preparedStatement.setString(1, equation);
        preparedStatement.executeUpdate();
    }
    public void add(String equation, double x) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE equations SET x = ? WHERE equation = ?;");
        preparedStatement.setDouble(1, x);
        preparedStatement.setString(2, equation);
        preparedStatement.execute();
    }
    public List<Question> findAll(double x) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM equations WHERE x = ?;");
        preparedStatement.setDouble(1, x);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Question> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Question(resultSet.getInt("id"), resultSet.getString("equation"), resultSet.getDouble("x")));
        }
        return list;

    }
}
