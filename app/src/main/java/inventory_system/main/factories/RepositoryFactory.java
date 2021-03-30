package inventory_system.main.factories;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.*;
import inventory_system.infra.database.postgre.*;

public class RepositoryFactory {

    public static StatusRepository makeStatusRepository (DatabaseConnection connection) {
        return new StatusPostgreRepository(connection);
    }

    public static BrandRepository makeBrandRepository (DatabaseConnection connection) {
        return new BrandPostgreRepository(connection);
    }

    public static UnitRepository makeUnitRepository (DatabaseConnection connection) {
        return new UnitPostgreRepository(connection);
    }

    public static CategoryRepository makeCategoryRepository (DatabaseConnection connection) {
        return new CategoryPostgreRepository(connection);
    }

    public static ProductRepository makeProductRepository (DatabaseConnection connection) {
        return new ProductPostgreRepository(connection);
    }

}
