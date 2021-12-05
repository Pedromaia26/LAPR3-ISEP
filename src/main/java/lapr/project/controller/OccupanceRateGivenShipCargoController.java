package lapr.project.controller;

import lapr.project.data.US208_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class OccupanceRateGivenShipCargoController {
    public OccupanceRateGivenShipCargoController() throws IOException, SQLException {
        US208_SQL sql = new US208_SQL();
        sql.demo(212180000, 3);
    }
}
