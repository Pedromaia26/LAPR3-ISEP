package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US310_SQL {

    private final Connection databaseConnection;
    private String occRate;

    public US310_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(String portId, int month, int year) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US310(?, ?, ?, ?)}")){

            statement.setString(1, portId);
            statement.setInt(2, month);
            statement.setInt(3, year);

            statement.registerOutParameter(4, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.occRate = statement.getString(4);

            StringBuilder data = new StringBuilder();
            data.append(occRate);
            FileOperation.writeToAFile("Output/US310_" + portId, data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US310_" + portId, data);
        }
    }

}
