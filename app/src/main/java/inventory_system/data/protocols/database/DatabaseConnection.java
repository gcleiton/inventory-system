package inventory_system.data.protocols.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DatabaseConnection {
    public void connect () throws SQLException, ClassNotFoundException;
    public void disconnect () throws SQLException;
    public boolean isConnected () throws SQLException;

    public void execute (String query) throws SQLException;
    public void execute (String query, List<String> params) throws SQLException;

    public List<Map<String, Object>> select (String query) throws SQLException;
    public List<Map<String, Object>> select (String query, List<String> params) throws SQLException;
}
