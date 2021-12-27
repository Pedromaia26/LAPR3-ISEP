package lapr.project.controller;

import lapr.project.data.US307_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class OccupancyOfEachWarehouseController {
    public OccupancyOfEachWarehouseController() throws IOException, SQLException {
        US307_SQL sql = new US307_SQL();
        sql.demo("27248");
    }
}
