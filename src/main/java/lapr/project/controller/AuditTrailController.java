package lapr.project.controller;

import lapr.project.data.US304_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class AuditTrailController {
    public AuditTrailController() throws IOException, SQLException {
        US304_SQL sql = new US304_SQL();
        sql.demo(6155496, 5);
    }
}
