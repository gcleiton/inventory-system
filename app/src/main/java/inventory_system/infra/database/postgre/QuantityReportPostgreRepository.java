package inventory_system.infra.database.postgre;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.QuantityReportRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.enumerations.QuantityType;
import inventory_system.main.factories.EntityFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuantityReportPostgreRepository implements QuantityReportRepository {
    private DatabaseConnection connection;

    public QuantityReportPostgreRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void add (Entity entity) throws SQLException {
        String query = "INSERT INTO quantity_reports (quantity, type_id, product_id) VALUES (?::integer, ?::integer, ?::integer)";
        List<String> params = this.getValues(entity);

        this.connection.execute(query, params);
    }

    private List<String> getValues (Entity entity) {
        List<String> params = new ArrayList<>();

        List<String> columns = this.getAvailableColumns();
        for (String column : columns) {
            params.add((String) entity.getAttribute(column));
        }

        return params;
    }

    private List<String> getAvailableColumns () {
        return new ArrayList<>() {{
            add("quantity");
            add("type_id");
            add("product_id");
        }};
    }

}
