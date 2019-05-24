package Restful;

import entities.Inventory;
import entities.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Evgeniya
 */
public class UsersDAO {

    private final String url = "jdbc:mysql://localhost:3306/inventorydb";
    private final String username = "admin";
    private final String userpassword = "admin";

    public ArrayList<Users> getAllUsers() throws SQLException {
        ArrayList<Users> allUsers = new ArrayList<>();
        String selectByEmailQuery = "SELECT * from users;";

        try (Connection connection = DriverManager.getConnection(url, username, userpassword);) {
            PreparedStatement pStatement = connection.prepareStatement(selectByEmailQuery);
            ResultSet user = pStatement.executeQuery();

            while (user.next()) {
                
                Users ps = new Users();
                ps.setId(user.getInt("id"));
                ps.setFirstname(user.getString("firstname"));
                ps.setLastname(user.getString("lastname"));
                ps.setStreet(user.getString("street"));
                ps.setCity(user.getString("city"));
                ps.setProvince(user.getString("province"));
                ps.setCountry(user.getString("country"));
                ps.setPostal(user.getString("postal_code"));
                ps.setEmail(user.getString("email"));
                ps.setPassword(user.getString("password"));

                allUsers.add(ps);
            }
        }
        return allUsers;
    }

    public int createUser(Users user) throws SQLException {
        int records = -1;

        String createQuery = "INSERT INTO users (id, firstname, lastname, street, city, province, "
                + "country, postal_code, email, password) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (
                Connection connection = DriverManager.getConnection(url, username, userpassword);
                PreparedStatement ps
                = connection.prepareStatement(createQuery);) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getStreet());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getProvince());
            ps.setString(7, user.getCountry());
            ps.setString(8, user.getPostal());
            ps.setString(9, user.getEmail());
            ps.setString(10, user.getPassword());

            //check  before creating a record if the same record already exists
            ArrayList<Users> existingUsers;

            existingUsers = getAllUsers();
            int j = 0;
            int numberOfUsers = existingUsers.size();

            for (int i = 0; i < numberOfUsers; i++) {
                if (!(existingUsers.get(i).getEmail()).equals(user.getEmail())) {
                    j = i;
                } else {
                    break;
                }
            }

            if (j != numberOfUsers - 1) {
                System.out.println("Transaction cannot be performed, because with this email already exists");
            } else {
                records = ps.executeUpdate();
            }
        }
        return records;
    }

    //validation of credentials
    public boolean passwordValidation(String email, String entered_passsword) throws SQLException {
        boolean validator = false;
        Users u = new Users();
        String password = "";
        String preparedSQL = "SELECT * FROM users WHERE email = ?";
        try (
                Connection connection = DriverManager.getConnection(url, username, userpassword);
                PreparedStatement ps
                = connection.prepareStatement(preparedSQL);) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                u.setId(resultSet.getInt("id"));
                u.setEmail(resultSet.getString("email"));
                u.setFirstname(resultSet.getString("firstname"));
                u.setLastname(resultSet.getString("lastname"));
                u.setStreet(resultSet.getString("street"));
                u.setCity(resultSet.getString("city"));
                u.setProvince(resultSet.getString("province"));
                u.setCountry(resultSet.getString("country"));
                u.setPostal(resultSet.getString("postal_code"));
                u.setPassword(resultSet.getString("password"));
            }
        }
        password = u.getPassword();

        if (password.equals(entered_passsword)) {
            validator = true;
        }
        return validator;
    }
}
