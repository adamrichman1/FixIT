package FixIT.Core;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * This class manages core database operations, including connect and closeConnection. Subclasses use these methods to
 * execute DB operations.
 */
public abstract class DBManager {
    private static String dbURL = "";

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

    /**
     * Used by subclasses to execute queries
     *
     * @param sql the SQL string containing the query to execute
     * @param queryParams an array of objects containing the query parameters to prepare the SQL statement with
     */
    protected ResultSet executeQuery(String sql, Object[] queryParams) {
        // TODO
        return null;
    }

    /**
     * Used by subclasses to execute update queries
     *
     * @param sql the SQL string containing the update query to execute
     * @param queryParams an array of objects containing the query parameters to prepare the SQL statement with
     */
    protected void executeUpdateQuery(String sql, Object[] queryParams) {
        // TODO
    }
}
