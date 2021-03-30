package inventory_system.infra.database.postgre;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.EntityFactory;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductPostgreRepository implements ProductRepository {
    private DatabaseConnection connection;

    public ProductPostgreRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    public List<Entity> loadAll () throws SQLException {
        List<Map<String, Object>> data = connection.select("select * from products");
        return EntityFactory.makeEntities(data);
    }

    public Entity loadByCode (String code) throws SQLException {
        String query = "SELECT * from products WHERE code = ?";

        List<String> params = new ArrayList<String>();
        params.add(code);

        List<Map<String, Object>> result = this.connection.select(query, params);

        if (result.size() > 0) {
            return EntityFactory.makeEntity(result.get(0));
        }

        return null;
    }

    public void add (Entity entity) throws SQLException {
        String query = "INSERT INTO products (code, name, model, description, quantity, brand_id, unit_id, status_id)" +
                " VALUES (?, ?, ?, ?, ?::integer, ?::integer, ?::integer, ?::integer)";

        List<String> params = this.getValues(entity);

        this.connection.execute(query, params);
    }

    public void update (String code, Entity entity) throws SQLException {
        String query = "UPDATE products SET " +
                "code = ?, name = ?, model = ?, description = ?, " +
                "quantity = ?::integer, brand_id = ?::integer, unit_id = ?::integer, status_id = ?::integer " +
                "WHERE code = ?";

        List<String> params = this.getValues(entity);
        params.add(code);

        this.connection.execute(query, params);
    }

    public void delete (String code) throws SQLException {
        String reportsQuery = "DELETE from quantity_reports WHERE product_id = '" + code + "'";
        this.connection.execute(reportsQuery);

        String query = "DELETE from products WHERE code = '" + code + "'";

        this.connection.execute(query);
    }

    public List<Entity> loadQuantityReports (String code) throws SQLException {
        String query = "SELECT * FROM quantity_reports WHERE product_id = '" + code + "'";
        List<Map<String, Object>> data = this.connection.select(query);
        return EntityFactory.makeEntities(data);
    }

    public List<Entity> loadCategories (String code) throws SQLException {
        String query = "SELECT DISTINCT c.id, c.name, c.description, c.created_at, c.updated_at " +
                "FROM categories c " +
                "JOIN category_product cp ON c.id = cp.category_id " +
                "JOIN products p ON p.code = cp.product_id " +
                "where p.code = '" + code + "'";

        List<Map<String, Object>> data = this.connection.select(query);
        return EntityFactory.makeEntities(data);
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
            add("code");
            add("name");
            add("model");
            add("description");
            add("quantity");
            add("brand_id");
            add("unit_id");
            add("status_id");
        }};
    }

}
