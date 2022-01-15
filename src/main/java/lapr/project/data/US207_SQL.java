package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US207_SQL {

    private final Connection databaseConnection;
    private int numberOfCargoManifest;
    private float avgContainer;

    public US207_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(int mmsi, int year) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US207(?, ?, ?, ?)}")){
            statement.setString(1, String.valueOf(mmsi));

            statement.setInt(2, year);

            statement.registerOutParameter(3, Types.INTEGER);

            statement.registerOutParameter(4, Types.FLOAT);

            statement.executeUpdate();

            this.numberOfCargoManifest = statement.getInt(3);

            this.avgContainer = statement.getFloat(4);

            StringBuilder data = new StringBuilder();
            data.append("Number of cargo manifests: " + numberOfCargoManifest + "\n");
            data.append("Number of containers per cargo manifest: " + avgContainer + "\n");
            FileOperation.writeToAFile("Output/US207_" + mmsi, data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US207_" + mmsi, data);
        }
    }


}
