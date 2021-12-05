package lapr.project.controller;

import lapr.project.data.US205_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class ListOfContainersOffloadedController {
    public ListOfContainersOffloadedController() throws IOException, SQLException {
        US205_SQL sql = new US205_SQL();
        sql.demo(212180000);
    }
}
