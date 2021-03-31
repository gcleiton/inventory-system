package inventory_system.data.protocols.database;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;
import java.util.List;

public interface BrandRepository {

    public List<Entity> loadAll () throws SQLException;

    public Entity loadById (int id) throws SQLException;

    public void add (Entity entity) throws SQLException;

    public void update (int id, Entity entity) throws SQLException;

    public void delete (int id) throws SQLException;

}
