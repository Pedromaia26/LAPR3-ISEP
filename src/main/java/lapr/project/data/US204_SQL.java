package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US204_SQL {

    private Connection databaseConnection;
    private String container_location;
    private FileOperation fileOperation;

    public US204_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int containerId) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US204(?,?)}");


            statement.setInt(1, containerId);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.container_location = statement.getString(2);

            System.out.println(statement.getString(2));

            StringBuilder data = new StringBuilder();
            data.append(container_location);
            fileOperation.writeToAFile("US204_" + containerId, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("US204_" + containerId, data);
        }
    }


}