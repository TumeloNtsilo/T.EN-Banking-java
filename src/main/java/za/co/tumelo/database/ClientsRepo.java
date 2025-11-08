package za.co.tumelo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientsRepo {

    public static void addClients(String full_name, String date_of_birth, int pin){
        String sql = "INSERT INTO clients (full_name, date_of_birth, pin) VALUES (?, ?, ?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, full_name);
            stmt.setString(2, date_of_birth);
            stmt.setInt(3, pin);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Client's details saved Successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving client's details " + e.getMessage());
        }

    }
}
