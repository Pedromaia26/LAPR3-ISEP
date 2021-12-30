package lapr.project.data;

import lapr.project.model.Border;
import lapr.project.model.Seadist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeadistStore {

    private List<Seadist> seadists = new ArrayList<>();

    public boolean addSeadist(Seadist seadist) {
        if (!this.seadists.contains(seadist))
            this.seadists.add(seadist);
        return true;
    }

    public Seadist getSeadist(String toPort, String fromPort) {
        for (Seadist seadist : seadists) {
            if (seadist.getToPortName().equals(toPort) && seadist.getFromPortName().equals(fromPort)) {
                return seadist;
            }
        }
        return null;
    }

    public List<Seadist> getSeadists() {
        return seadists;
    }

    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Seadist seadist = (Seadist) object;
        boolean returnValue = false;

        try {
            saveSeadistToDatabase(databaseConnection, seadist);
            //Save changes.
            returnValue = true;

        } catch (SQLException ex) {
            System.out.println(seadist.getFromPortId() + ":" + seadist.getToPortId());
            Logger.getLogger(CountryStore.class.getName())
                    .log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    private void saveSeadistToDatabase(DatabaseConnection databaseConnection, Seadist seadist) throws SQLException {
        if (!isSeadistOnDatabase(databaseConnection, seadist))
            insertSeadistOnDatabase(databaseConnection, seadist);
    }

    protected boolean isSeadistOnDatabase(DatabaseConnection databaseConnection, Seadist seadist) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("select * from seadist where from_Portid = ? AND to_Portid = ?");
        preparedStatement.setInt(1, seadist.getFromPortId());
        preparedStatement.setInt(2, seadist.getToPortId());

        boolean isSeadistDynDataOnDatabase;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            isSeadistDynDataOnDatabase = resultSet.next();
            resultSet.close();

        } finally {
            preparedStatement.close();
        }
        return isSeadistDynDataOnDatabase;
    }

    private void insertSeadistOnDatabase(DatabaseConnection databaseConnection, Seadist seadist) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand =
                "insert into seadist(from_Portid, to_Portid, from_Countryname, to_Countryname, from_Port, to_Port, sea_distance) values (?, ?, ?, ?, ?, ?, ?)";

        executeSeadistStatementOnDatabase(databaseConnection, seadist,
                sqlCommand);
    }

    private void executeSeadistStatementOnDatabase(DatabaseConnection databaseConnection, Seadist seadist, String sqlCommand) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        PreparedStatement saveClientPreparedStatement = connection.prepareStatement(sqlCommand);
        saveClientPreparedStatement.setString(1, String.valueOf(seadist.getFromPortId()));
        saveClientPreparedStatement.setString(2, String.valueOf(seadist.getToPortId()));
        saveClientPreparedStatement.setString(3, seadist.getFromCountryName());
        saveClientPreparedStatement.setString(4, seadist.getToCountryName());
        saveClientPreparedStatement.setString(5, seadist.getFromPortName());
        saveClientPreparedStatement.setString(6, seadist.getToPortName());
        saveClientPreparedStatement.setInt(7, seadist.getSeaDistance());
        try{
            saveClientPreparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("The Seadist of the " + seadist.getToPortId() + " was not imported/updated.");
            databaseConnection.registerError(e);
        } finally {
            saveClientPreparedStatement.close();
        }
    }
}