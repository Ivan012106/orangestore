package ua.edu.nung.fit;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Метод для створення користувача (Create)
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        }
    }

    // Метод для отримання всіх користувачів (Read)
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }
        return users;
    }

    public void performTransactionTask(User u1, User u2) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // Вимикаємо автокоміт
            try {
                // Виконуємо кілька операцій
                createUserInTransaction(conn, u1);
                createUserInTransaction(conn, u2);

                conn.commit(); // Якщо все добре - зберігаємо
            } catch (SQLException e) {
                conn.rollback(); // Якщо помилка - скасовуємо все!
                throw e;
            }
        }
    }

    private void createUserInTransaction(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        }
    }

    public void createUserSafe(User user) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); //
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.executeUpdate();
                conn.commit(); //
            } catch (SQLException e) {
                conn.rollback(); //
                throw e;
            }
        }
    }

    public void createUserWithRollback(User user) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // [cite: 35] Початок транзакції
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.executeUpdate();

                conn.commit(); // [cite: 37] Підтвердження змін
            } catch (SQLException e) {
                conn.rollback(); // [cite: 39] Відкат у разі помилки
                throw e;
            }
        }
    }
    // Метод для видалення (Delete)
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}