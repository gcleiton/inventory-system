package inventory_system.main.factories;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.infra.adapters.JdbcDatabaseConnectionAdapter;

public class AdapterFactory {

    public static DatabaseConnection makeDatabaseConnection () {
        return new JdbcDatabaseConnectionAdapter();
    }

}

