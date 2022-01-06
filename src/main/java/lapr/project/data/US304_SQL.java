package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US304_SQL {

    private Connection databaseConnection;
    private String auditTrail;
    private FileOperation fileOperation;

    public US304_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int container, int cargoManifest) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US304(?, ?, ?)}")){

            statement.setString(1, String.valueOf(container));

            statement.setString(2, String.valueOf(cargoManifest));

            statement.registerOutParameter(3, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.auditTrail = statement.getString(3);

            StringBuilder data = new StringBuilder();
            data.append(auditTrail);
            fileOperation.writeToAFile("Output/US304_" + container + "_" +  cargoManifest, data);
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US304_" + container + "_" +  cargoManifest, data);
        }
    }

}
