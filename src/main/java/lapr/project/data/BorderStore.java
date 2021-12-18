package lapr.project.data;



import lapr.project.controller.App;
import lapr.project.model.Border;
import lapr.project.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorderStore {

    private List<Border> borders = new ArrayList<>();

    public boolean addBorder(Border border) {
        if (!this.borders.contains(border))
            this.borders.add(border);
        return true;
    }

    public List<Country> getBordersCountry(String name) {
        List<Country> list = new ArrayList<>();
        for (Border border : borders) {
            if (border.getCountryname1().equals(name)) {
                list.add(App.getInstance().getCompany().getCountryStore().getCountry(border.getCountryname2()));
            }
        }
        return list;
    }

    public List<Border> getBorders() {
        return borders;
    }

    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Border border = (Border) object;
        boolean returnValue = false;

        try {
            saveBorderToDatabase(databaseConnection, border);
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

    private void saveBorderToDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        if (!isBorderOnDatabase(databaseConnection, border) && isCountry1OnDatabase(databaseConnection, border) && isCountry2OnDatabase(databaseConnection, border)) {
            System.out.println("OLA");
            insertBorderOnDatabase(databaseConnection, border);
        }
    }

    protected boolean isBorderOnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from border where countryName1 = ? AND countryName2 = ?");
        preparedStatement.setString(1, border.getCountryname1());
        preparedStatement.setString(2, border.getCountryname2());

        boolean isBorderDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isBorderDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isBorderDynDataOnDatabase;
    }

    protected boolean isCountry1OnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from country where name = ?");
        preparedStatement.setString(1, border.getCountryname1());

        boolean isBorderDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isBorderDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isBorderDynDataOnDatabase;
    }

    protected boolean isCountry2OnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from country where name = ?");
        preparedStatement.setString(1, border.getCountryname2());

        boolean isBorderDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isBorderDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isBorderDynDataOnDatabase;
    }

    private void insertBorderOnDatabase(DatabaseConnection databaseConnection, Border border) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into border(countryName1, countryName2) values (?, ?)";

        executeLocationStatementOnDatabase(databaseConnection, border,
                sqlCommand);
    }

    private void executeLocationStatementOnDatabase(DatabaseConnection databaseConnection, Border border, String sqlCommand) throws SQLException {
        System.out.println(border.getCountryname1() + border.getCountryname2());
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, border.getCountryname1());
        saveClientPreparedStatement.setString(2, border.getCountryname2());

        try{
            saveClientPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The Border of the " + border.getCountryname1() + " was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveClientPreparedStatement.close();
        }
    }
}