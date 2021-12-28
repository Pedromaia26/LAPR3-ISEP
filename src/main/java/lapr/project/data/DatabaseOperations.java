package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.*;
import lapr.project.ui.Login;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseOperations {
    int num_user_id = 1;
    public boolean saveShip(DatabaseConnection databaseConnection, Object object) {
        Ship ship = (Ship) object;
        boolean returnValue = false;

        try {
            saveShipToDatabase(databaseConnection, ship);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void saveShipToDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
//        if (isShipOnDatabase(databaseConnection, ship))
//            updateShipOnDatabase(databaseConnection, ship);
//        else
            insertShipOnDatabase(databaseConnection, ship);
    }

    private void insertShipOnDatabase(DatabaseConnection databaseConnection,
                                         Ship ship) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into ship(mmsi,name,imo,num_energy_gen,gen_pow_out,callsign,vessel,length,width,cap,cap_x,cap_y,cap_z,draft,userid_shipcaptain) values (" + ship.getMmsi() + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipStatementOnDatabase(databaseConnection, ship,
                sqlCommand);
    }

    private void executeShipStatementOnDatabase(DatabaseConnection databaseConnection, Ship ship, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        if (num_user_id != 3) num_user_id++;
        else num_user_id = 1;

        saveClientPreparedStatement.setString(1, ship.getShipName());
        saveClientPreparedStatement.setString(2, ship.getImo());
        saveClientPreparedStatement.setInt(3, ship.getGenerators());
        saveClientPreparedStatement.setInt(4, ship.getGenertorPowerOutput());
        saveClientPreparedStatement.setString(5, ship.getCallSign());
        saveClientPreparedStatement.setString(6, ship.getVesselType());
        saveClientPreparedStatement.setFloat(7, ship.getLength());
        saveClientPreparedStatement.setFloat(8, ship.getWidth());
        saveClientPreparedStatement.setInt(9, ship.getCapacity());
        saveClientPreparedStatement.setInt(10, 20);
        saveClientPreparedStatement.setInt(11, 5);
        saveClientPreparedStatement.setInt(12, 10);
        saveClientPreparedStatement.setFloat(13, ship.getDraft());
        saveClientPreparedStatement.setString(14, "shipcaptain" + num_user_id);
        try{
            saveClientPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The Ship with the " + ship.getMmsi() + " MMSI code was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveClientPreparedStatement.close();
        }
    }

    protected boolean isShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        PreparedStatement preparedStatement =
                databaseConnection.getConnection().prepareStatement("select * from ship where mmsi = ?");

        preparedStatement.setString(1, String.valueOf(ship.getMmsi()));

        boolean isShipOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isShipOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isShipOnDatabase;
    }

    protected void updateShipOnDatabase(DatabaseConnection databaseConnection, Ship ship) throws SQLException {
        String command = "update ship set name = ?, imo = ?, num_energy_gen = ?, gen_pow_out = ?, callSign = ?, vessel = ?, length = ?, width = ?, cap = ?, cap_x = ?, cap_y = ?, cap_z = ?, draft = ? where mmsi = " + ship.getMmsi();

        executeShipStatementOnDatabase(databaseConnection, ship, command);
    }

    public boolean saveShipDynData(DatabaseConnection databaseConnection, Object object, String shipMmsi) {
        ShipDynData shipDynData = (ShipDynData) object;
        boolean returnValue = false;

        try {
            saveShipDynDataToDatabase(databaseConnection, shipDynData, shipMmsi);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void saveShipDynDataToDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String shipMmsi) throws SQLException {
        if (isShipDynDataOnDatabase(databaseConnection, shipDynData, shipMmsi))
            updateShipDynDataOnDatabase(databaseConnection, shipDynData, shipMmsi);
        else
        insertShipDynDataOnDatabase(databaseConnection, shipDynData, shipMmsi);
    }

    protected boolean isShipDynDataOnDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String shipMmsi) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from ship_positioning_data where base_date_time = ?");
        Timestamp ts = new Timestamp(shipDynData.getBaseDateTime().getTime());
        preparedStatement.setTimestamp(1, ts);

        boolean isShipDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isShipDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isShipDynDataOnDatabase;
    }

    protected void updateShipDynDataOnDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String shipMmsi) throws SQLException {
        String command = "update ship_positioning_data set latitude = ?, longitude = ?, sog = ?, cog = ?, ship_heading = ?, ship_position = ?, transceiver_class = ?, cargo = ? where ship_mmsi = ? and base_date_time = ?";

        executeShipDynDataUpdateStatementOnDatabase(databaseConnection, shipDynData, command, shipMmsi);
    }

    private void executeShipDynDataUpdateStatementOnDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String sqlCommand, String shipMmsi) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        Timestamp ts = new Timestamp(shipDynData.getBaseDateTime().getTime());
        PreparedStatement saveShipDynDataPreparedStatement = connection.prepareStatement(sqlCommand);
        saveShipDynDataPreparedStatement.setFloat(1, Float.parseFloat(shipDynData.getLatitude()));
        saveShipDynDataPreparedStatement.setFloat(2, Float.parseFloat(shipDynData.getLongitude()));
        saveShipDynDataPreparedStatement.setFloat(3, shipDynData.getSog());
        saveShipDynDataPreparedStatement.setFloat(4, shipDynData.getCog());
        saveShipDynDataPreparedStatement.setString(5, shipDynData.getHeading());
        saveShipDynDataPreparedStatement.setFloat(6, shipDynData.getPosition());
        saveShipDynDataPreparedStatement.setString(7, shipDynData.getTransceiver());
        saveShipDynDataPreparedStatement.setString(8, shipDynData.getCargo());
        saveShipDynDataPreparedStatement.setString(9, shipMmsi);
        saveShipDynDataPreparedStatement.setTimestamp(10, ts);
        try{
            saveShipDynDataPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The ShipDynData of the ship with the " + shipMmsi + " MMSI code was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveShipDynDataPreparedStatement.close();
        }
    }

    private void insertShipDynDataOnDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String shipMmsi) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "insert into ship_positioning_data(base_date_time,latitude,longitude,sog,cog,ship_heading,ship_position,transceiver_class,cargo,ship_mmsi) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        executeShipDynDataStatementOnDatabase(databaseConnection, shipDynData, sqlCommand, shipMmsi);
    }

    private void executeShipDynDataStatementOnDatabase(DatabaseConnection databaseConnection, ShipDynData shipDynData, String sqlCommand, String shipMmsi) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        Timestamp ts = new Timestamp(shipDynData.getBaseDateTime().getTime());
        PreparedStatement saveShipDynDataPreparedStatement = connection.prepareStatement(sqlCommand);
        saveShipDynDataPreparedStatement.setTimestamp(1, ts);
        saveShipDynDataPreparedStatement.setFloat(2, Float.parseFloat(shipDynData.getLatitude()));
        saveShipDynDataPreparedStatement.setFloat(3, Float.parseFloat(shipDynData.getLongitude()));
        saveShipDynDataPreparedStatement.setFloat(4, shipDynData.getSog());
        saveShipDynDataPreparedStatement.setFloat(5, shipDynData.getCog());
        saveShipDynDataPreparedStatement.setString(6, shipDynData.getHeading());
        saveShipDynDataPreparedStatement.setFloat(7, shipDynData.getPosition());
        saveShipDynDataPreparedStatement.setString(8, shipDynData.getTransceiver());
        saveShipDynDataPreparedStatement.setString(9, shipDynData.getCargo());
        saveShipDynDataPreparedStatement.setString(10, shipMmsi);
        try{
            saveShipDynDataPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The ShipDynData of the ship with the " + shipMmsi + " MMSI code was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveShipDynDataPreparedStatement.close();
        }
    }

    public boolean savePort(DatabaseConnection databaseConnection, Object object) {
        Port port = (Port) object;
        boolean returnValue = false;

        try {
            savePortToDatabase(databaseConnection, port);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void savePortToDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        if (isPortOnDatabase(databaseConnection, port))
            updatePortOnDatabase(databaseConnection, port);
        else
            insertPortOnDatabase(databaseConnection, port);
    }

    private void insertPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into port(id,name,location_latitude,location_longitude) values (?, ?, ?, ?)";

        executePortStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    private void executePortStatementOnDatabase(DatabaseConnection databaseConnection, Port port, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, String.valueOf(port.getCode()));
        saveClientPreparedStatement.setString(2, port.getName());
        saveClientPreparedStatement.setFloat(3, port.getLatitude());
        saveClientPreparedStatement.setFloat(4, port.getLongitude());
        try{
            saveClientPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The Port with the name of " + port.getName() + " was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveClientPreparedStatement.close();
        }
    }

    protected boolean isPortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        PreparedStatement preparedStatement =
                databaseConnection.getConnection().prepareStatement("select * from port where id = ?");

        preparedStatement.setString(1, String.valueOf(port.getCode()));

        boolean isPortOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isPortOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isPortOnDatabase;
    }

    protected void updatePortOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        String command = "update port set name = ?, location_latitude = ?, location_longitude = ? where id = " + port.getCode();

        executeLocationStatementOnDatabase(databaseConnection, port, command);
    }

    public boolean saveLocation(DatabaseConnection databaseConnection, Object object) {
        Port port = (Port) object;
        boolean returnValue = false;

        try {
            saveLocationToDatabase(databaseConnection, port);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void saveLocationToDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        if (isLocationOnDatabase(databaseConnection, port))
            updateLocationOnDatabase(databaseConnection, port);
        else
        insertLocationOnDatabase(databaseConnection, port);
    }

    protected boolean isLocationOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        PreparedStatement preparedStatement =
                databaseConnection.getConnection().prepareStatement("select * from location where latitude = ? and longitude = ?");

        preparedStatement.setFloat(1, port.getLatitude());
        preparedStatement.setFloat(2, port.getLongitude());

        boolean isPortOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isPortOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isPortOnDatabase;
    }

    protected void updateLocationOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        String command = "update location set country_name = ? where latitude = ? and longitude = ?";

        executeLocationStatementOnDatabase(databaseConnection, port, command);
    }

    private void insertLocationOnDatabase(DatabaseConnection databaseConnection, Port port) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =  "insert into location(country_name,latitude,longitude) values (?, ?, ?)";

        executeLocationStatementOnDatabase(databaseConnection, port, sqlCommand);
    }

    private void executeLocationStatementOnDatabase(DatabaseConnection databaseConnection, Port port, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, port.getCountry().getName());
        saveClientPreparedStatement.setFloat(2, port.getLatitude());
        saveClientPreparedStatement.setFloat(3, port.getLongitude());

        try{
            saveClientPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The Location of the " + port.getName() + " was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveClientPreparedStatement.close();
        }
    }

    public void importContriesFromDatabase() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        try {

            Connection connection = databaseConnection.getConnection();

            String sql = "select * from country";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                Country country = new Country(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(1) , rs.getFloat(5), rs.getString(6), rs.getFloat(7), rs.getFloat(8));
                App.getInstance().getCompany().getCountryStore().addCountry(country);

            }
            rs.close();
            st.close();

        }catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }

    public void importBordersFromDatabase() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        try {

            Connection connection = databaseConnection.getConnection();

            String sql = "select * from border";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                Border border = new Border(rs.getString(1), rs.getString(2));
                App.getInstance().getCompany().getBorderStore().addBorder(border);

            }
            rs.close();
            st.close();

        }catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }

    public void importSeadistsFromDatabase() {
        DatabaseConnection databaseConnection = App.getInstance().getDatabaseConnection();
        try {

            Connection connection = databaseConnection.getConnection();

            String sql = "select * from seadist";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                Seadist seadist = new Seadist(rs.getString(3), Integer.parseInt(rs.getString(2)), rs.getString(5), rs.getString(4), Integer.parseInt(rs.getString(2)), rs.getString(6), rs.getInt(7));
                App.getInstance().getCompany().getSeadistStore().addSeadist(seadist);
            }
            rs.close();
            st.close();

        }catch(Exception e) {
            System.out.println("Something went wrong!");
        }

    }
}
