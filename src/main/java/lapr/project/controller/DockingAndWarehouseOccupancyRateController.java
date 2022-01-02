package lapr.project.controller;

import lapr.project.data.US310_SQL;

import java.io.IOException;
import java.sql.SQLException;

public class DockingAndWarehouseOccupancyRateController {
    public DockingAndWarehouseOccupancyRateController() throws IOException, SQLException {
        US310_SQL sql = new US310_SQL();
        sql.demo("27248", 1, 2022);
    }
}
