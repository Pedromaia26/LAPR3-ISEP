package lapr.project.controller;

import lapr.project.data.US206_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class ListOfContainersLoadedController {
    public ListOfContainersLoadedController() throws IOException, SQLException {
        US206_SQL sql = new US206_SQL();
        sql.demo(212180000);
    }
}
