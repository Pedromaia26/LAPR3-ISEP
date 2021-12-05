package lapr.project.controller;

import lapr.project.data.US210_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class ShipsAvailableNextMondayController {
    public ShipsAvailableNextMondayController() throws IOException, SQLException {
        US210_SQL sql = new US210_SQL();
        sql.demo();
    }
}
