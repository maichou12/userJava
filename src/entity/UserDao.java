package entity;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gestionusers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public void addUser(Users user) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Users (email, password, idRole) VALUES (?, ?, ?)")) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswordHashed());
            statement.setInt(3, user.getIdRole());

            statement.executeUpdate();
            System.out.println("Insertion réussie !");
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public List<Users> listUsers() {
        List<Users> users = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users")) {

            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPasswordHashed(resultSet.getString("password"));
                user.setIdRole(resultSet.getInt("idRole"));
                // Initialisez roles correctement avec un nom fictif ou récupérez-le depuis la base de données
                user.setRoles(new Roles(user.getIdRole(), "admin"));
                users.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }

        return users;
    }
    public List<Roles> listRoles() {
        List<Roles> roles = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Roles")) {

            while (resultSet.next()) {
                Roles role = new Roles();
                role.setId(resultSet.getInt("id"));
                role.setNom(resultSet.getString("nom"));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée
        }

        return roles;
    }

}
