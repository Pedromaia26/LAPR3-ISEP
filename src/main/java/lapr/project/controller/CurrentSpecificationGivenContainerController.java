package lapr.project.controller;

import lapr.project.data.US204_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class CurrentSpecificationGivenContainerController {
    public CurrentSpecificationGivenContainerController() throws IOException, SQLException {
        US204_SQL sql = new US204_SQL();
        sql.demo(7222282);
    }
}
