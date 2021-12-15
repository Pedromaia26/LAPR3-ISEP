package lapr.project.controller;

import lapr.project.data.CheckLogin_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public LoginController(String username, String password) throws SQLException, IOException {
        CheckLogin_SQL cl = new CheckLogin_SQL();
        cl.demo(username, password);
    }
}
