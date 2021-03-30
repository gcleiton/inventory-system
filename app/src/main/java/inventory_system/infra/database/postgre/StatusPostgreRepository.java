package inventory_system.infra.database.postgre;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.StatusRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.EntityFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatusPostgreRepository implements StatusRepository {
    private DatabaseConnection connection;

    public StatusPostgreRepository(DatabaseConnection connection) {
        this.connection = connection;
    }

    public List<Entity> loadAll () throws SQLException {
        List<Map<String, Object>> statuses = connection.select("select * from statuses");
        return EntityFactory.makeEntities(statuses);
    }

    public Entity loadById (int id) throws SQLException {
        String query = "SELECT * from statuses WHERE id = ?::integer";

        List<String> params = new ArrayList<String>();
        params.add(String.valueOf(id));

        List<Map<String, Object>> result = this.connection.select(query, params);

        return EntityFactory.makeEntity(result.get(0));
    }

    public void add (Entity entity) throws SQLException {
        String query = "INSERT INTO statuses (name, description) VALUES (?, ?)";
        List<String> params = new ArrayList<String>();
        params.add(entity.getAttribute("name").toString());
        params.add(entity.getAttribute("description").toString());

        this.connection.execute(query, params);
    }

    public void update (int id, Entity entity) throws SQLException {
        String query = "UPDATE statuses SET name = ?, description = ? WHERE id = ?::integer";

        List<String> params = new ArrayList<String>();
        params.add(entity.getAttribute("name").toString());
        params.add(entity.getAttribute("description").toString());
        params.add(String.valueOf(id));

        this.connection.execute(query, params);
    }

    public void delete (int id) throws SQLException {
        String query = "DELETE from statuses WHERE id = ?::integer";

        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));

        this.connection.execute(query, params);
    }

}
