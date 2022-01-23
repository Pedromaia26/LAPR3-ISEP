package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US404_SQL {

    private Connection databaseConnection;
    private String listOfPaths;
    private FileOperation fileOperation;

    public US404_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo() throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US404(?)}");

            statement.registerOutParameter(1, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfPaths = statement.getString(1);

            StringBuilder data = new StringBuilder();
            data.append(listOfPaths);
            fileOperation.writeToAFile("Output/US404", data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US404", data);
        }
    }


}
