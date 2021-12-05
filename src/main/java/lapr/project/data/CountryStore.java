package lapr.project.data;

import lapr.project.model.Country;
import lapr.project.model.ShipDynData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryStore implements Persistable{

    private List<Country> Countries = new ArrayList<>();

    public boolean validateCountry(Country country){
        if (country == null)
            return false;
        return getCountry(country);
    }

    public Country addCountry(Country country){
        if (!validateCountry(country)){
            return null;
        }
        else if (!this.Countries.contains(country))
            this.Countries.add(country);
        return country;
    }

    public boolean getCountry(Country country){
        for (Country c : Countries){
            if (country.equals(c)){
                return true;
            }
            else if (!country.getContinent().equals(c.getContinent()) && country.getName().equals(c.getName())){
                return false;
            }
        }
        return true;
    }

    public List<Country> getCountries() {
        return Countries;
    }

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Country country = (Country) object;
        boolean returnValue = false;

        try {
            saveCountryToDatabase(databaseConnection, country);

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
        String command = "update country set continent = ? where name = ?";

        executeCountryUpdateStatementOnDatabase(databaseConnection, country, command);
    }

    private void executeCountryUpdateStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, country.getContinent());
        saveClientPreparedStatement.setString(2, country.getName());
        saveClientPreparedStatement.executeUpdate();
    }

    private void insertCountryOnDatabase(DatabaseConnection databaseConnection, Country country) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into country(name, continent) values (?, ?)";

        executeCountryStatementOnDatabase(databaseConnection, country,
                sqlCommand);
    }

    private void executeCountryStatementOnDatabase(DatabaseConnection databaseConnection, Country country, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, country.getName());
        saveClientPreparedStatement.setString(2, country.getContinent());
        saveClientPreparedStatement.executeUpdate();
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        return false;
    }

    @Override
    public Object getObject(DatabaseConnection databaseConnection, String id) {
        return null;
    }
}
