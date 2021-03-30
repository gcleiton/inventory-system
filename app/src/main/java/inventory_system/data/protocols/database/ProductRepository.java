package inventory_system.data.protocols.database;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    public List<Entity> loadAll () throws SQLException;

    public Entity loadByCode (String code) throws SQLException;

    public void add (Entity entity) throws SQLException;

    public void update (String code, Entity entity) throws SQLException;

    public void delete (String code) throws SQLException;

    public List<Entity> loadQuantityReports (String code) throws SQLException;

    public List<Entity> loadCategories (String code) throws SQLException;

}
