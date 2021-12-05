package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US208_SQL {

    private Connection databaseConnection;
    private String ocRate;
    private FileOperation fileOperation;

    public US208_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int mmsi, int cargoid) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US208(?, ?, ?)}");


            statement.setString(1, String.valueOf(mmsi));

            statement.setInt(2, cargoid);

            statement.registerOutParameter(3, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.ocRate = statement.getString(3);

            StringBuilder data = new StringBuilder();
            data.append(ocRate);
            fileOperation.writeToAFile("Output/US208_" + mmsi, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US208_" + mmsi, data);
        }
    }


}
