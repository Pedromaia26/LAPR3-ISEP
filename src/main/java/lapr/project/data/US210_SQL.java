package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US210_SQL {

    private final Connection databaseConnection;
    private String listOfShips;

    public US210_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo() throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US210(?)}")){
            statement.registerOutParameter(1, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.listOfShips = statement.getString(1);

            StringBuilder data = new StringBuilder();
            data.append(listOfShips);
            FileOperation.writeToAFile("Output/US210", data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US210", data);
        }
    }


}
