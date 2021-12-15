/*package lapr.project.controller;

import lapr.project.model.Company;

public class App {

    private Company company;

    private App(){

        company = new Company();

    }

    public Company getCompany() {
        return company;
    }

    private static App singleton = null;

    public static App getInstance(){
        if(singleton == null){
            synchronized (App.class){
                singleton = new App();
            }
        }
        return singleton;
    }
}*/

package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.model.Company;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private final DatabaseConnection databaseConnection;

    private final Company company;

    private String username;



    private App (){
        company=new Company();
        this.databaseConnection = initializeConnection();
    }

    public Company getCompany(){
        return company;
    }

    private static App singleton = null;

    public static App getInstance(){
        if(singleton == null){
            synchronized (App.class){
                singleton=new App();
            }
        }
        return singleton;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method responsible for initializing the database connection.
     * @return the database connection
     */
    private DatabaseConnection initializeConnection(){
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(App.class.getName())
                    .log(Level.SEVERE, null, exception);
        }

        return databaseConnection;
    }

    public DatabaseConnection getDatabaseConnection(){
        return this.databaseConnection;
    }
}
