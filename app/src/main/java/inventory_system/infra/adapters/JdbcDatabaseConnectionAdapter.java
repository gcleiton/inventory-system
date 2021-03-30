package inventory_system.infra.adapters;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.main.config.Environment;

import java.sql.*;
import java.util.*;

public class JdbcDatabaseConnectionAdapter implements DatabaseConnection {

    private Connection connection;
    private Map<String, String> configs;

    private final static String driver = "org.postgresql.Driver";

    public JdbcDatabaseConnectionAdapter () {
        this.connection = null;
        this.configs = getConfigs();
    }

    @Override
    public void connect () {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(buildUri(), configs.get("username"), configs.get("password"));

        } catch (SQLException e) {
            System.out.println("Could not establish a connection to the database.");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found.");
        }
    }

    @Override
    public void disconnect () {
        try {
            this.connection.close();
            this.connection = null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected () throws SQLException {
        return !this.connection.isClosed() && this.connection == null;
    }

    public void execute (String query) throws SQLException {
        this.execute(query, null);
    }

    public void execute (String query, List<String> params) throws SQLException {
        PreparedStatement statement = this.buildStatement(query);
        this.setParams(statement, params);
        statement.execute();
    }

    public List<Map<String, Object>> select (String query) throws SQLException {
        return this.select(query, null);
    }

    public List<Map<String, Object>> select (String query, List<String> params) throws SQLException {
        PreparedStatement statement = this.buildStatement(query);
        this.setParams(statement, params);
        ResultSet result = statement.executeQuery();
        return this.toList(result);
    }

    private void setParams (PreparedStatement statement, List<String> params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                this.bindParam(statement, i + 1, params.get(i));
            }
        }
    }

    private void bindParam (PreparedStatement statement, int key, String value) throws SQLException {
        statement.setObject(key, value);
    }

    private PreparedStatement buildStatement (String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }

    private List<Map<String, Object>> toList(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columns = metaData.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (result.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(metaData.getColumnName(i), result.getObject(i));
            }
            rows.add(row);
        }

        return rows;
    }

    private Map<String, String> getConfigs () {
        Environment env = Environment.getInstance();
        Map<String, String> configs = new HashMap<String, String>();

        configs.put("driver", env.get("DB_DRIVER"));
        configs.put("host", env.get("DB_HOST"));
        configs.put("port", env.get("DB_PORT"));
        configs.put("database", env.get("DB_DATABASE"));
        configs.put("username", env.get("DB_USERNAME"));
        configs.put("password", env.get("DB_PASSWORD"));

        return configs;
    }

    private String buildUri () {
        return "jdbc:" + configs.get("driver")  + "://" + configs.get("host") + ":" + configs.get("port") + "/" + configs.get("database");
    }
}
