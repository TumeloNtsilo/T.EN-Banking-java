package za.co.tumelo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    public static boolean isPinOkay(int pin){
        String query = "SELECT * FROM clients WHERE pin = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {

        stmt.setInt(1, pin);
        ResultSet result = stmt.executeQuery();

        return result.next();

        } catch (SQLException e) {
            return false;
        }
    }
}
