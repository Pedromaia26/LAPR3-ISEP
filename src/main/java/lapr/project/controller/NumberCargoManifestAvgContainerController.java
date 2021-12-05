package lapr.project.controller;

import lapr.project.data.US207_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class NumberCargoManifestAvgContainerController {
    public NumberCargoManifestAvgContainerController() throws IOException, SQLException {
        US207_SQL sql = new US207_SQL();
        sql.demo(212180000, 2021);
    }
}
