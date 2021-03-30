package inventory_system.data.protocols.database;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;
import java.util.List;

public interface QuantityReportRepository {

    public void add (Entity entity) throws SQLException;

}
