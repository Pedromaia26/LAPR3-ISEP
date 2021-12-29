package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US306_SQL {

    private Connection databaseConnection;
    private String occRate;
    private FileOperation fileOperation;

    public US306_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(String portId) throws SQLException, IOException {
        try {

            CallableStatement statement = databaseConnection.prepareCall("{CALL US306(?, ?)}");

            statement.setString(1, portId);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.occRate = statement.getString(2);

            StringBuilder data = new StringBuilder();
            data.append(occRate);
            fileOperation.writeToAFile("Output/US306_" + portId, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US306_" + portId, data);
        }
    }

}
