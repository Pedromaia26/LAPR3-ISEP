package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US306_SQL {

    private final Connection databaseConnection;
    private String occRate;

    public US306_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(String portId) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US306(?, ?)}")){

            statement.setString(1, portId);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.occRate = statement.getString(2);

            StringBuilder data = new StringBuilder();
            data.append(occRate);
            FileOperation.writeToAFile("Output/US306_" + portId, data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US306_" + portId, data);
        }
    }

}
