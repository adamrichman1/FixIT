package FixIT.Core;

import java.sql.Connection;

/**
 * This class manages core database operations, including connect(), closeConnection(), createTable(), & dropTable().
 */
public abstract class DBManager {
    /**
     * Used by subclasses to connect to the DB before executing SQL
     *
     * @param dbURL the URL of the DB to connect to
     * @return a Connection object that allows DB queries
     */
    protected Connection connect(String dbURL) {
        // TODO
        return null;
    }

    /**
     * Used by subclasses to close Connection objects once a query is complete
     *
     * @param c the connection object to close
     */
    protected void closeConnection(Connection c) {
        // TODO
    }

    /**
     * Used by subclasses to create specific types of tables (e.g. appointment table)
     *
     * @param dbURL the URL of the DB to connect to
     * @param sql the SQL string containing a query to create a table
     */
    protected void createTable(String dbURL, String sql) {
        // TODO
    }

    /**
     * Used to drop (delete) a table, for development purposes
     *
     * @param dbURL the URL of the DB to connect to
     * @param tableName the name of the table to drop
     */
    protected void dropTable(String dbURL, String tableName) {
        // TODO
    }
}
