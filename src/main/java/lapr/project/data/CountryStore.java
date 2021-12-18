package lapr.project.data;

import lapr.project.model.Country;
import lapr.project.model.ShipDynData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryStore{

    private List<Country> Countries = new ArrayList<>();

    public boolean addCountry(Country country){
        if (country == null){
            return false;
        }
        if (!this.Countries.contains(country))
            this.Countries.add(country);
        return true;
    }

    public Country getCountry(String continent, String name){
        for (Country c : Countries){
            if (c.getContinent().equals(continent) && c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public Country getCountry(String name){
        for (Country c : Countries){
            if (c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public List<Country> getCountries() {
        return Countries;
    }

    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Country country = (Country) object;
        boolean returnValue = false;

        try {
            saveCountryToDatabase(databaseConnection, country);
            saveLocationToDatabase(databaseConnection, country);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            Logger.getLogger(CountryStore.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void saveCountryToDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        if (isCountryOnDatabase(databaseConnection, country))
            updateCountryOnDatabase(databaseConnection, country);
        else
        insertCountryOnDatabase(databaseConnection, country);
    }

    private void saveLocationToDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        if (!isLocationOnDatabase(databaseConnection, country))
            insertLocationOnDatabase(databaseConnection, country);
    }

    protected boolean isCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from country where name = ?");
        preparedStatement.setString(1, country.getName());

        boolean isShipDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isShipDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isShipDynDataOnDatabase;
    }

    protected void updateCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        String command = "update country set continent = ?, alpha2_code = ?, alpha3_code = ?, population = ?, capital = ?, latitude = ?, longitude = ? where name = ?";

        executeCountryUpdateStatementOnDatabase(databaseConnection, country, command);
    }

    private void executeCountryUpdateStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, country.getContinent());
        saveClientPreparedStatement.setString(2, country.getAlpha2_code());
        saveClientPreparedStatement.setString(3, country.getAlpha3_code());
        saveClientPreparedStatement.setFloat(4, country.getPopulation());
        saveClientPreparedStatement.setString(5, country.getCapital());
        saveClientPreparedStatement.setFloat(6, country.getLatitude());
        saveClientPreparedStatement.setFloat(7, country.getLongitude());
        saveClientPreparedStatement.setString(8, country.getName());
        saveClientPreparedStatement.executeUpdate();
    }

    protected boolean isLocationOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from location where latitude = ? AND longitude = ?");
        preparedStatement.setFloat(1, country.getLatitude());
        preparedStatement.setFloat(2, country.getLongitude());

        boolean isLocationDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isLocationDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isLocationDynDataOnDatabase;
    }

    private void insertCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into country(name, continent, alpha2_code, alpha3_code, population, capital, latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";

        executeCountryStatementOnDatabase(databaseConnection, country,
                sqlCommand);
    }

    private void insertLocationOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into location(latitude, longitude, country_name) values (?, ?, ?)";

        executeLocationStatementOnDatabase(databaseConnection, country,
                sqlCommand);
    }

    private void executeCountryStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, country.getName());
        saveClientPreparedStatement.setString(2, country.getContinent());
        saveClientPreparedStatement.setString(3, country.getAlpha2_code());
        saveClientPreparedStatement.setString(4, country.getAlpha3_code());
        saveClientPreparedStatement.setFloat(5, country.getPopulation());
        saveClientPreparedStatement.setString(6, country.getCapital());
        saveClientPreparedStatement.setFloat(7, country.getLatitude());
        saveClientPreparedStatement.setFloat(8, country.getLongitude());
        saveClientPreparedStatement.executeUpdate();
    }

    private void executeLocationStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setFloat(1, country.getLatitude());
        saveClientPreparedStatement.setFloat(2, country.getLongitude());
        saveClientPreparedStatement.setString(3, country.getName());
        saveClientPreparedStatement.executeUpdate();
    }
}
