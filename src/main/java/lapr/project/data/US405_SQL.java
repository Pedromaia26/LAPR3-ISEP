package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US405_SQL {

    private Connection databaseConnection;
    private String ocRate;
    private FileOperation fileOperation;

    public US405_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(String mmsi, String date1, String date2) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US405(?, ?, ?, ?)}");


            statement.setString(1, mmsi);

            statement.setString(2, date1);

            statement.setString(3, date2);

            statement.registerOutParameter(4, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.ocRate = statement.getString(4);

            System.out.println(statement.getString(4));

            StringBuilder data = new StringBuilder();
            data.append(ocRate);
            fileOperation.writeToAFile("Output/US405_" + mmsi, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US405_" + mmsi, data);
        }
    }


}
