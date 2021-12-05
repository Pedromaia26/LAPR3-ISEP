package lapr.project.controller;

import lapr.project.data.US209_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class OccupanceRateGivenShipDateController {
    public OccupanceRateGivenShipDateController() throws IOException, SQLException {
        US209_SQL sql = new US209_SQL();
        sql.demo(212180000, "2022-03-25 00:00:00");
    }
}
