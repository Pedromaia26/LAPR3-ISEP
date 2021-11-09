package lapr.project.controller;

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
}
