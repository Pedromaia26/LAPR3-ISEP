package lapr.project.ui;

import lapr.project.controller.LoginController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login {

    Scanner sc = new Scanner(System.in);
    String username, password;

    public Login() {

    }

    public void run() throws IOException, SQLException {

        System.out.println("Insert your username: ");
        username = sc.nextLine();
        System.out.println("Insert your password: ");
        password = sc.nextLine();

        LoginController loginController = new LoginController(username, password);


    }
}
