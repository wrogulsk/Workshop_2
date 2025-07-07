package pl.coderslab.entity;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;
import pl.coderslab.entity.User;

public class UserDao {
    Scanner scanner = new Scanner(System.in);
    User[] users = new User[0];

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String PRINT_USER_QUERY = "SELECT * FROM users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        User newUser = new User();
        try (Connection conn = DbUtil.connect()) {
            String query = "Select * from users where id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newUser = new User(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4));
            }
            System.out.println(newUser.getUsername());
            System.out.println(newUser.getEmail());
            System.out.println(newUser.getPassword());
            System.out.println(newUser.getId());

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return newUser;
    }


    public void update(User user) {
        try (Connection conn = DbUtil.connect()) {
            System.out.println("Podaj nowa nazwe uzytkownika: ");
            String nowaNazwa = scanner.nextLine();
            System.out.println("Podaj nowy email uzytkownika: ");
            String nowyEmail = scanner.nextLine();
            System.out.println("Podaj nowe haslo uzytkownika: ");
            String noweHaslo = scanner.nextLine();
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.setUsername(nowaNazwa));
            statement.setString(2, user.setEmail(nowyEmail));
            statement.setString(3, user.setPassword(hashPassword(noweHaslo)));
            statement.setInt(4, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.connect()) {
            System.out.println("Wybrales pracownika o ID: " + userId);
            DbUtil.remove(conn, "users", userId);
            System.out.println("Pracownik zostal usuniety z bazy danych");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        try (Connection conn = DbUtil.connect()) {
            String query = "Select * from users";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User newUser = new User(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4));
                users = addToArray(newUser, users);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        return users;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }
}

