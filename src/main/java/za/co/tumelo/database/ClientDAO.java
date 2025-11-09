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

    public static String getNameUsingPin(int pin){
        String query = "SELECT full_name FROM clients WHERE pin = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, pin);
            ResultSet result = stmt.executeQuery();

            if(result.next()){
                return result.getString("full_name");
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
