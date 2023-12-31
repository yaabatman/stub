package bell.stub.database;

import bell.stub.models.User;

import java.sql.*;

public class BdConnection {

    private static final String DB_URL = "jdbc:postgresql://192.168.1.135:5432/postgres";
    private static final String DB_USER = "myuser";
    private static final String DB_PASSWORD = "mypassword";

    public static User selectUserByLogin(String selectLogin) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM table1 t1 JOIN table2 t2 ON t1.login = t2.login WHERE t1.login LIKE (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, selectLogin);
                if (preparedStatement.execute()) {
                    ResultSet resultSet = preparedStatement.getResultSet();
                    if (resultSet.next()) {
                        String login = resultSet.getString("login");
                        String password = resultSet.getString("password");
                        java.sql.Date date = resultSet.getDate("date");
                        String email = resultSet.getString("email");
                        user = new User(login, password, date, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int insertUser(User user) {
        String query = "INSERT INTO table1 (login, password, date) VALUES (?, ?, ?); INSERT INTO table2 (login, email) VALUES (?, ?)";
        int res = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setDate(3, new java.sql.Date(user.getDate().getTime()));
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getEmail());

                res += preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


}



