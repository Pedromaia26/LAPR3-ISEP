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

    private Connection databaseConnection;
    private String listOfPaths;
    private FileOperation fileOperation;

    public US407_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(String port) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US407(?, ?)}");

            statement.setString(1, port);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfPaths = statement.getString(2);

            System.out.println(statement.getString(2));

            StringBuilder data = new StringBuilder();
            data.append(listOfPaths);
            fileOperation.writeToAFile("Output/US407_" + port, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US407_" + port, data);
        }
    }


}

