package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US312_SQL {

    private final Connection databaseConnection;
    private String container_location;

    public US312_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }
    public void demo(int containerId, String c_username) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US312(?,?,?)}")){
            statement.setInt(1, containerId);

            statement.setString(2, c_username);

            statement.registerOutParameter(3, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.container_location = statement.getString(3);

            StringBuilder data = new StringBuilder();
            data.append(container_location);
            FileOperation.writeToAFile("Output/US312" + containerId, data);

        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US312_" + containerId, data);
        }
    }
}