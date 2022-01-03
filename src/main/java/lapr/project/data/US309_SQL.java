package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US309_SQL {
    private Connection databaseConnection;
    private FileOperation fileOperation;

    public US309_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int id, String ship_mmsi, String warehouse_id, int status) throws SQLException, IOException {

            CallableStatement statement = databaseConnection.prepareCall("{CALL US309(?, ?, ?, ?)}");

            statement.setInt(1, id);
            statement.setString(2, ship_mmsi);
            statement.setString(3, warehouse_id);
            statement.setInt(4, status);

            statement.executeUpdate();

            statement.close();
    }
}
