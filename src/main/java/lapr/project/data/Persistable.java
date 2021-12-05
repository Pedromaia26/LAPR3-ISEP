package lapr.project.data;

public interface Persistable {
    /**
     * Save an objet to the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean save(DatabaseConnection databaseConnection, Object object);

    /**
     * Delete an object from the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean delete(DatabaseConnection databaseConnection, Object object);

    /**
     * Get an object from the database.
     *
     * @param databaseConnection
     * @param id
     * @return Operation success.
     */
    Object getObject(DatabaseConnection databaseConnection, String id);
}
