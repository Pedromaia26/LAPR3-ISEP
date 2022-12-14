package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;

import java.io.IOException;
import java.sql.*;

public class CargoManifest_SQL {

    private final DatabaseConnection databaseConnection;

    public CargoManifest_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public void demo(int cargo_id) throws SQLException, IOException {

        int container_id = 0;
        int container_x;
        int container_y;
        int container_z;
        StringBuilder data = new StringBuilder();

        try {

            Connection connection = databaseConnection.getConnection();

            String sql = "select container_id, container_x, container_y, container_z from container_cargoManifest where cargo_manifest_id = ? AND stage_id = 1";

            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, cargo_id);
                try (ResultSet rs = st.executeQuery()) {

                    while (rs.next()) {
                        if (container_id != 0) {
                            data.append("\n");
                        }
                        container_id = rs.getInt(1);
                        container_x = rs.getInt(2);
                        container_y = rs.getInt(3);
                        container_z = rs.getInt(4);
                        data.append(container_id + " " + container_x + " " + container_y + " " + container_z);
                    }
                    FileOperation.writeToAFile("Documentation/Sprint3/ARQCP/US313/containers.txt", data);
                }catch (SQLException exception){
                    exception.printStackTrace();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
            }

        }catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }
}
