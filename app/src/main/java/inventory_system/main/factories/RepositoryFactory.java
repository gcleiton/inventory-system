package inventory_system.main.factories;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.StatusRepository;
import inventory_system.infra.database.postgre.BrandPostgreRepository;
import inventory_system.infra.database.postgre.StatusPostgreRepository;

public class RepositoryFactory {

    public static StatusRepository makeStatusRepository (DatabaseConnection connection) {
        return new StatusPostgreRepository(connection);
    }

    public static BrandRepository makeBrandRepository (DatabaseConnection connection) {
        return new BrandPostgreRepository(connection);
    }

}
