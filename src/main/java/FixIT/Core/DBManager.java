package FixIT.Core;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

/**
 * This class controls core DB operations, such as creating/dropping tables and connecting/closing DB connections
 */
public abstract class DBManager {

    private static Logger logger = LoggerFactory.getLogger(DBManager.class);
    private static String dbURL = "jdbc:postgresql://localhost:5432/postgres";

    /**
     * Used to connect to the DB
     *
     * @return a Connection object for the DB
     */
    private static Connection connect(String dbURL) {
        Connection c = null;
        try {
            if (dbURL != null) c = DriverManager.getConnection(dbURL);
            else throw new IllegalStateException();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        if(c == null){
            logger.error(">>> ERROR: System failed to connect to DB");
            System.exit(1);
        }
        return c;
    }

    /**
     * Used to close a DB connection
     *
     * @param c the Connection object to close
     */
    private static void close(Connection c) {
        try {
            c.close();
        } catch(SQLException e) {
            logger.error(">>> ERROR: Couldn't close DB connection object");
            System.exit(1);
        }
    }

    /**
     * Used to drop a table
     *
     * @param table the table to drop
     */
    static void dropTable(String table){
        String sql = "DROP TABLE IF EXISTS " + table;
        executeUpdate(sql);
    }

    /**
     * Used to execute an SQL query
     *
     * @param sql the query string
     * @param queryParams parameters for the query specific in sql
     * @return the ResultSet containing data from the query
     */
    protected static ResultSet executeQuery(String sql, Object... queryParams) {
        Connection conn = connect(dbURL);
        try {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement = prepareStatement(statement, queryParams);
            ResultSet resultSet = statement.executeQuery();
            close(conn);
            return resultSet;
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't execute query <<<", e);
            close(conn);
            System.exit(1);
            return null;
        }
    }

    /**
     * Used to convert a ResultSet object into a specific class
     *
     * @param rs the ResultSet object containing match data from the query
     * @param id the id of the column to extract from the ResultSet
     * @param c the Class to convert the ResultSet column to
     * @return the deserialized value
     */
    protected static <S> S deserializeResultSetCol(ResultSet rs, String id, Class<S> c) {
        try {
            if (rs.next()) {
                return deserializeString(rs.getString(id), c);
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't extract ResultSet column " + id, e);
            System.exit(1);
        }
        return null;
    }

    /**
     * Used to convert a ResultSet object into a String
     *
     * @param rs the ResultSet object containing match data from the query
     * @param id the id of the column to extract from the ResultSet
     * @return the deserialized value
     */
    protected static String getString(ResultSet rs, String id) {
        try {
            if (rs.next()) {
                return rs.getString(id);
            }
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't extract ResultSet column " + id, e);
            System.exit(1);
        }
        return null;
    }

    /**
     * Used to deserialize an object
     *
     * @param data the data to deserialize
     * @param c the class of generic type S that the data will be deserialized into
     * @param <S> the generic type that the data will be deserialized into
     * @return the deserialized value
     */
    protected static <S> S deserializeString(String data, Class<S> c) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, c);
        } catch (IOException e) {
            logger.error(">>> ERROR: Couldn't deserialize data into type " + c.getName(), e);
            System.exit(1);
        }
        return null;
    }

    /**
     * Used to execute an SQL update query
     *
     * @param sql the query string to execute
     * @param queryParams parameters for the query specific in sql
     * @return the ResultSet of the update
     */
    protected static ResultSet executeUpdate(String sql, Object... queryParams) {
        Connection conn = connect(dbURL);
        try {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement = prepareStatement(statement, queryParams);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            close(conn);
            return rs;
        } catch(SQLException e){
            logger.error(">>> ERROR: Couldn't execute update query", e);
            close(conn);
            System.exit(1);
            return null;
        }
    }

    /**
     * Used to prepare a statement before executing a query
     *
     * @param statement the query statement to prepare with query parameters
     * @param queryParams the parameters to prepare the statement with
     * @return the prepared statement
     */
    private static PreparedStatement prepareStatement(PreparedStatement statement, Object... queryParams) {
        try {
            int i = 1;
            for (Object queryParam: queryParams) {
                statement.setObject(i++, queryParam);
            }
            return statement;
        } catch (SQLException e) {
            logger.error(">>> ERROR: Couldn't prepare statement", e);
            System.exit(1);
            return null;
        }
    }
}
