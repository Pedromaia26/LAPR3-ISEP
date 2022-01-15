package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US309_SQL {
    private final Connection databaseConnection;

    public US309_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(int id, String ship_mmsi, String warehouse_id, int status) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US309(?, ?, ?, ?)}")) {

            statement.setInt(1, id);
            statement.setString(2, ship_mmsi);
            statement.setString(3, warehouse_id);
            statement.setInt(4, status);

            statement.executeUpdate();

        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append(e.getMessage());
            FileOperation.writeToAFile("Output/US309", data);
        }
    }
}
