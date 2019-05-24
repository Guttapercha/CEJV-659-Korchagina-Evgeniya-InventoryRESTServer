package Restful;

import entities.Inventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author Evgeniya
 */
public class InventoryDAO {

    private final String url = "jdbc:mysql://localhost:3306/inventorydb";
    private final String username = "admin";
    private final String password = "admin";

    public ArrayList<Inventory> getAllInventoryByEmail(String email) throws SQLException {
        ArrayList<Inventory> inv = new ArrayList<>();
        String selectByEmailQuery = "SELECT * from inventory where email = ? and status = ?;";

        try (Connection connection = DriverManager.getConnection(url, username, password);) {
            PreparedStatement pStatement = connection.prepareStatement(selectByEmailQuery);
            pStatement.setString(1, email);
            pStatement.setBoolean(2, true);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                Inventory b = new Inventory();
                b.setId(resultSet.getInt("id"));
                b.setEmail(resultSet.getString("email"));
                b.setArtist(resultSet.getString("artist"));
                b.setAlbum(resultSet.getString("album"));
                b.setYear(resultSet.getInt("year"));
                b.setState(resultSet.getString("state"));
                b.setState_detailed(resultSet.getString("state_detailed"));
                b.setUpc(resultSet.getString("upc"));
                b.setNotes(resultSet.getString("notes"));
                b.setStatus(resultSet.getBoolean("status"));
                inv.add(b);
            }
        }
        return inv;
    }
    
    public ArrayList<Inventory> getAllInventory() throws SQLException {
        ArrayList<Inventory> inv = new ArrayList<>();
        String selectByEmailQuery = "SELECT * from inventory where status = ?;";

        try (Connection connection = DriverManager.getConnection(url, username, password);) {
            PreparedStatement pStatement = connection.prepareStatement(selectByEmailQuery);
            pStatement.setBoolean(1, true);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                Inventory b = new Inventory();
                b.setId(resultSet.getInt("id"));
                b.setEmail(resultSet.getString("email"));
                b.setArtist(resultSet.getString("artist"));
                b.setAlbum(resultSet.getString("album"));
                b.setYear(resultSet.getInt("year"));
                b.setState(resultSet.getString("state"));
                b.setState_detailed(resultSet.getString("state_detailed"));
                b.setUpc(resultSet.getString("upc"));
                b.setNotes(resultSet.getString("notes"));
                b.setStatus(resultSet.getBoolean("status"));
                inv.add(b);
            }
        }
        return inv;
    }

    public Inventory findRecordById(int id) throws SQLException {

        Inventory b = new Inventory();
        String preparedSQL = "SELECT * FROM inventory WHERE id = ? and status = ?";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps
                = connection.prepareStatement(preparedSQL);) {
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                b.setId(resultSet.getInt("id"));
                b.setEmail(resultSet.getString("email"));
                b.setArtist(resultSet.getString("artist"));
                b.setAlbum(resultSet.getString("album"));
                b.setYear(resultSet.getInt("year"));
                b.setState(resultSet.getString("state"));
                b.setState_detailed(resultSet.getString("state_detailed"));
                b.setUpc(resultSet.getString("upc"));
                b.setNotes(resultSet.getString("notes"));
                b.setStatus(resultSet.getBoolean("status"));
            }
        }
        return b;
    }

    public int createInventory(Inventory inv) throws SQLException {
        int records = -1;

        String createQuery = "INSERT INTO inventory (id, email, artist, album, year, state, "
                + "state_detailed, upc, notes, status) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps
                = connection.prepareStatement(createQuery);) {
            ps.setInt(1, inv.getId());
            ps.setString(2, inv.getEmail());
            ps.setString(3, inv.getArtist());
            ps.setString(4, inv.getAlbum());
            ps.setInt(5, inv.getYear());
            ps.setString(6, inv.getState());
            ps.setString(7, inv.getState_detailed());
            ps.setString(8, inv.getUpc());
            ps.setString(9, inv.getNotes());
            ps.setBoolean(10, inv.isStatus());

            //check  before creating a record if the same record already exists
            ArrayList<Inventory> existingRecords;

            existingRecords = getAllInventory();
            int j = 0;
            int numberOfRecords = existingRecords.size();

            for (int i = 0; i < numberOfRecords; i++) {
                if (!existingRecords.get(i).equals(inv)) {
                    j = i;
                } else {
                    break;
                }
            }

            if (j != numberOfRecords - 1) {
                System.out.println("Transaction cannot be performed, because such record already exists");
            } else {
                records = ps.executeUpdate();
            }
        }
        return records;
    }

    ////update record///////
    public int updateInventory(Inventory inv) throws SQLException {

        int result = -1;
        String updateQuery = "UPDATE inventory SET email = ?, artist = ?, album = ?, year = ?, "
                + "state = ?, state_detailed = ?, upc = ?, notes = ?, status = ? WHERE id = ?";

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(updateQuery);) {

            ps.setInt(10, inv.getId());
            ps.setString(1, inv.getEmail());
            ps.setString(2, inv.getArtist());
            ps.setString(3, inv.getAlbum());
            ps.setInt(4, inv.getYear());
            ps.setString(5, inv.getState());
            ps.setString(6, inv.getState_detailed());
            ps.setString(7, inv.getUpc());
            ps.setString(8, inv.getNotes());
            ps.setBoolean(9, inv.isStatus());

            //check  before creating a record if the same record already exists
            ArrayList<Inventory> existingRecords;

            existingRecords = getAllInventoryByEmail(inv.getEmail());
            int j = 0;
            int numberOfRecords = existingRecords.size();

            for (int i = 0; i < numberOfRecords; i++) {
                if (!existingRecords.get(i).equals(inv)) {
                    j = i;
                } else {
                    break;
                }
            }

            if (j != numberOfRecords - 1) {
                System.out.println("Transaction cannot be performed, because such record already exists");
            } else {
                result = ps.executeUpdate();
            }
        }
        return result;
    }

    ////delete record by update its status to false///////
    public int deleteInventory(int id) throws SQLException {

        int result = -1;
        Inventory inv = findRecordById(id);

        String updateQuery = "UPDATE inventory SET email = ?, artist = ?, album = ?, year = ?, "
                + "state = ?, state_detailed = ?, upc = ?, notes = ?, status = ? WHERE id = ?";

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps
                = connection.prepareStatement(updateQuery);) {

            ps.setInt(10, inv.getId());
            ps.setString(1, inv.getEmail());
            ps.setString(2, inv.getArtist());
            ps.setString(3, inv.getAlbum());
            ps.setInt(4, inv.getYear());
            ps.setString(5, inv.getState());
            ps.setString(6, inv.getState_detailed());
            ps.setString(7, inv.getUpc());
            ps.setString(8, inv.getNotes());
            ps.setBoolean(9, false);

            result = ps.executeUpdate();
        }
        return result;
    }

}
