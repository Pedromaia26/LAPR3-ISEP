package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.ui.Login;
import lapr.project.utils.FileOperation;
import oracle.ucp.proxy.annotation.Pre;

import java.io.IOException;
import java.sql.*;

public class CheckLogin_SQL {

    private DatabaseConnection databaseConnection;
    private String container_location;
    private FileOperation fileOperation;
    private boolean exists = false;

    public CheckLogin_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection();
    }

    public void demo(String username, String password) throws SQLException, IOException {

        try {

            Connection connection = databaseConnection.getConnection();

            String sql = "select * from usertable";

            try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
                while (rs.next()) {
                    if (rs.getString(1).equals(username)) {
                        exists = true;
                        if (!rs.getString(2).equals(password)) {
                            exists = false;
                            break;
                        }
                    }
                }

                if (exists == false) {
                    System.out.printf("The typed information is incorrect. Try again!\n");
                    Login newLogin = new Login();
                    newLogin.run();
                } else {
                    App.getInstance().setUsername(username);
                }
            } catch (SQLException exception){
                exception.printStackTrace();
            }

        }catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }
}
