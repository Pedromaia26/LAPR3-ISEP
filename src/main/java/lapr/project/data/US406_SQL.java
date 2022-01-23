package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US406_SQL {

    private final Connection databaseConnection;
    private String listOfPaths;

    public US406_SQL(){
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(String mmsi, float limite) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US406(?, ?, ?)}")){

            statement.setString(1, mmsi);

            statement.setFloat(2, limite);

            statement.registerOutParameter(3, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfPaths = statement.getString(3);

            System.out.println(statement.getString(3));

            StringBuilder data = new StringBuilder();
            data.append(listOfPaths);
            FileOperation.writeToAFile("Output/US406_" + mmsi, data);

        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US406_" + mmsi, data);
        }
    }


}

