package lapr.project.controller;

import lapr.project.data.US306_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class OccupancyOfEachWarehouseController {
    public OccupancyOfEachWarehouseController() throws IOException, SQLException {
        US306_SQL sql = new US306_SQL();
        sql.demo("27248");
    }
}
