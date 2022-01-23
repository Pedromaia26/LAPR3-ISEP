package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US407_SQL {

    private final Connection databaseConnection;
    private String listOfPaths;

    public US407_SQL(){
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(String port) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US407(?, ?)}")){

            statement.setString(1, port);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfPaths = statement.getString(2);

            System.out.println(statement.getString(2));

            StringBuilder data = new StringBuilder();
            data.append(listOfPaths);
            FileOperation.writeToAFile("Output/US407_" + port, data);

        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US407_" + port, data);
        }
    }


}

