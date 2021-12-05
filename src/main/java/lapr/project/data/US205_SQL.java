package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US205_SQL {

    private Connection databaseConnection;
    private String listOfContainers;
    private FileOperation fileOperation;

    public US205_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int mmsi) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US205(?, ?)}");


            statement.setString(1, String.valueOf(mmsi));

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfContainers = statement.getString(2);

            StringBuilder data = new StringBuilder();
            data.append(listOfContainers);
            fileOperation.writeToAFile("Output/US205_" + mmsi, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US205_" + mmsi, data);
        }
    }


}
