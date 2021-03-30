package inventory_system.domain.usecases;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;

public interface UpdateProduct {
    public void update (String code, Entity entity) throws SQLException;
}
