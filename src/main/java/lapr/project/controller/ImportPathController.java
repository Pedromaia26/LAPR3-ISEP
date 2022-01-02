package lapr.project.controller;

import lapr.project.data.US305_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class ImportPathController {
    public ImportPathController() throws IOException, SQLException {
        US305_SQL sql = new US305_SQL();
        sql.demo("client1", 3058855);
    }
}
