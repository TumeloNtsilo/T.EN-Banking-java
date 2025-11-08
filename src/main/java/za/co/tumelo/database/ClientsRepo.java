package za.co.tumelo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientsRepo {

    public void addClients(String full_name, String date_of_birth, String email, String pin){
        String sql = "INSERT INTO clients (full_name, date_of_birth, email, pin) VALUES (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, full_name);
            stmt.setString(2, date_of_birth);
            stmt.setString(3, email);
            stmt.setString(4, pin);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Client's details saved Successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving client's details " + e.getMessage());
        }

    }
}
