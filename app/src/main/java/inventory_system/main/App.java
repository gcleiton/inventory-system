package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.StatusRepository;
import inventory_system.data.protocols.database.UnitRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.EntityFactory;
import inventory_system.main.factories.RepositoryFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            UnitRepository unitRepository = RepositoryFactory.makeUnitRepository(conn);

            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("abbreviation", "rb");
            newEntity.setAtribute("name", "teste");
            newEntity.setAtribute("description", "Medida para medir");

            unitRepository.add(newEntity);

            Entity unit = unitRepository.loadById(1);
            System.out.println(unit.getAttributes());

            List<Entity> units = unitRepository.loadAll();
            System.out.println(units);

            unit.setAtribute("abbreviation", "kg");
            unit.setAtribute("name", "Kilograma");

            unitRepository.update(1, unit);

            unit = unitRepository.loadById(1);
            System.out.println(unit.getAttributes());

            unitRepository.delete(1);

            units = unitRepository.loadAll();
            System.out.println(units);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
