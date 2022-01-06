package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US305_SQL {

    private Connection databaseConnection;
    private String listOfPaths;
    private FileOperation fileOperation;

    public US305_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(String client__id, int container__id) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US305(?, ? ,?)}")){

            statement.setString(1, client__id);

            statement.setInt(2, container__id);

            statement.registerOutParameter(3, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfPaths = statement.getString(3);

            StringBuilder data = new StringBuilder();
            data.append(listOfPaths);
            fileOperation.writeToAFile("Output/US305_" + client__id, data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US305_" + client__id, data);
        }
    }


}
